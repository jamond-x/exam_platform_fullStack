package DAO;

import database.DBPool;
import entity.Token;
import entity.User;
import utils.token.ProcessToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class Operate implements APIUser {
//    private DBInit dbInstance = null;
    private DBPool dbInstance = null;

    private Connection con = null;
    private PreparedStatement statement = null;
    public Operate(){
        this.dbInstance = new DBPool();
    }

    //    public Operate(){
//       this.dbInstance = new DBInit();
//    }
//    private void init(){
//        if(this.dbInstance == null || this.con == null){
//            this.dbInstance = new DBInit();
//            this.con = this.dbInstance.getConnection();
//        }
//    }
    private void init(){
        if(this.dbInstance == null){
            this.dbInstance = new DBPool();
//            this.con = this.dbInstance.getConnection();
        }else if(this.con == null){
            this.con = this.dbInstance.getCon();
        }
    }
    @Override
    public boolean login(String id, String pw) throws Exception {
        init();
        String sql = "SELECT * FROM users WHERE id=?";
        this.statement = this.con.prepareStatement(sql);
        this.statement.setString(1,id);
        ResultSet res = this.statement.executeQuery();
        boolean flag = false;
        if(res.next()){
            String password =  res.getString("password");
            if (password.equals(pw)){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public boolean signup(String id, String pw, String email) throws Exception {
        init();
        String sql = "INSERT INTO users (id) VALUES";
        sql += "("+id+")";
        boolean res;
        try{
            this.statement = this.con.prepareStatement(sql);
            System.out.println(sql);
            res = this.statement.execute(sql);
            sql = "UPDATE users SET password=?,email=? WHERE id=?";
            this.statement = this.con.prepareStatement(sql);
            this.statement.setString(1,pw);
            this.statement.setString(2,email);
            this.statement.setString(3,id);
            int update = this.statement.executeUpdate();
            if(update == 1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }finally {
            this.con.close();
        }
    }

    @Override
    public boolean updateToken(String userId, String token, boolean isUpdate) throws Exception {
        // isUpdate ??????true ??????token??? false ??????
        init();
        String sql = "";
        if(isUpdate){
            sql = "UPDATE users SET token=? WHERE id=?";
        }else {
            sql = "UPDATE users SET token='' WHERE id=?";
        }
        this.statement = this.con.prepareStatement(sql);
        if(isUpdate){
            this.statement.setString(1,token);
            this.statement.setString(2,userId);
        }else {
            this.statement.setString(1,userId);
        }
        if(this.statement.executeUpdate() == 1){
            return  true;
        }
        return false;
    }

    @Override
    public User getUserInfo(String userId) throws Exception {
        init();
        String sql = "SELECT * FROM users WHERE id=?";
        this.statement = this.con.prepareStatement(sql);
        this.statement.setString(1,userId);
        ResultSet res = this.statement.executeQuery();
        User user = null;
        if(res.next()){
            user = new User(
                    res.getString("id"),
                    res.getString("name"),
                    res.getString("gender"),
                    res.getString("description"),
                    res.getString("avatar"),
                    res.getString("email"),
                    res.getString("admin")
                    );
        }
        return user == null ? null:user;
    }

    @Override
    public User updateUserInfo(User user) throws Exception{
        init();
        System.out.println(user.getGender());
        String sql = "";
        sql = "UPDATE users SET name=?,gender=?,description=?,avatar=?,email=? WHERE id=?";
        this.statement = this.con.prepareStatement(sql);
        this.statement.setString(1,user.getName());
        this.statement.setString(2,user.getGender());
        this.statement.setString(3,user.getDescription());
        this.statement.setString(4,user.getAvatar());
        this.statement.setString(5,user.getEmail());
        this.statement.setString(6,user.getId());

        try {
            if(this.statement.executeUpdate() == 1){
                return user;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.con.close();
        }
        return null;
    }


    @Override
    public boolean verifyToken(Token token) throws Exception {
        return ProcessToken.verifyToken(token.getToken());
    }

  @Override
  public boolean isAdmin(String id) throws Exception {
      init();
      String sql = "SELECT admin FROM users WHERE id=?";
      this.statement = this.con.prepareStatement(sql);
      this.statement.setString(1,id);
      try{
        ResultSet res = this.statement.executeQuery();
        if(res.next()){
          if( res.getString("admin").equals("1")){
            return true;
          }else {
            return false;
          }
        }else{
          return false;
        }
      }catch (Exception e)
      {
        e.printStackTrace();
        return false;
      }finally {
        this.con.close();
      }
  }

  @Override
  public HashSet<User> allUsers() throws Exception {
      init();
      HashSet<User> resSet = new HashSet<>();
      String sql = "SELECT * FROM users";
      this.statement = this.con.prepareStatement(sql);
      try{
        ResultSet res = this.statement.executeQuery();
        while(res.next()){
          String id = res.getString("id");
          String name = res.getString("name");
          String gender = res.getString("gender");
          String description = res.getString("description");
          String avatar = res.getString("avatar");
          String email = res.getString("email");
          String admin = res.getString("admin");
          resSet.add(new User(
            id == null ||id == ""? "??????": id ,
            name == null || id == ""? "??????": name,
            gender == null ||gender == ""? "??????": gender,
            description == null || description == ""? "??????": description,
            avatar == null ||avatar == ""? "??????": avatar,
            email == null || email == ""? "??????":email,
            admin == null || admin ==""? "??????": admin
          ));
        }
        return resSet;
      }catch (Exception e){
        e.printStackTrace();
        return null;
      }finally {
        this.con.close();
      }
  }
}
