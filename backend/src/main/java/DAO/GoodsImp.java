package DAO;

import database.DBPool;
import entity.Goods;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class GoodsImp implements GoodsDAO {
  private DBPool dbInstance = null;
  private Connection con = null;
  private PreparedStatement statement = null;
  public GoodsImp(){
    this.dbInstance = new DBPool();
  }

  private void init(){
    if(this.dbInstance == null){
      this.dbInstance = new DBPool();
//            this.con = this.dbInstance.getConnection();
    }else if(this.con == null){
      this.con = this.dbInstance.getCon();
    }
  }

  @Override
  public boolean addGoods(Goods good) throws Exception {
    init();
    String sql = "INSERT INTO goods (name,id,description,storeId,price) VALUES";
    sql += "('"+good.getName()+"','"+good.getId()+"','"+good.getDescription()+"','"+good.getStoreId()+"',"+good.getPrice()+")";
    boolean res = false;
    try{
      this.statement = this.con.prepareStatement(sql);
      res = this.statement.execute(sql);
    }catch (Exception e){
      e.printStackTrace();
      return false;
    }finally {
      this.con.close();
    }
    if(!res){
      return  true;
    }else{
      return  false;
    }
  }

  @Override
  public boolean deleteGoods(String id) throws Exception {
    init();
    String sql = "DELETE FROM `goods` WHERE id = ?";
    try{
      this.statement = this.con.prepareStatement(sql);
      this.statement.setString(1, id);
      boolean res = this.statement.execute();
      if(!res){
        return true;
      }
    }catch (Exception e){
      e.printStackTrace();
      return false;
    }finally {
      this.con.close();
    }
    return false;
  }

  @Override
  public Goods updateGoods(Goods good) throws Exception {
    init();
    String sql = "";
      try{
        sql = "UPDATE goods SET name=?,id=?,description=?,storeId=?,price=?,placeOfShipment=?,brand=?,imgUrl=?,comment=? WHERE id=?";
        this.statement = this.con.prepareStatement(sql);
        this.statement.setString(1, good.getName());
        this.statement.setString(2, good.getId());
        this.statement.setString(3,good.getDescription());
        this.statement.setString(4,good.getStoreId());
        this.statement.setInt(5,good.getPrice());
        this.statement.setInt(6,good.getPlaceOfShipment());
        this.statement.setString(7,good.getBrand());
        this.statement.setString(8,good.getImgUrl());
        this.statement.setString(9,good.getComment());
        this.statement.setString(10,good.getId());
        System.out.println(sql);
        if(this.statement.executeUpdate() == 1){
          return  good;
        }
      }catch (Exception e){
        e.printStackTrace();
      }finally {
        this.con.close();
      }
      return null;
  }

  @Override
  public  HashSet<Goods> queryGoods() throws Exception {
    init();
    String sql = "SELECT * FROM goods";
    HashSet<Goods> set = new HashSet<>();
    this.statement = this.con.prepareStatement(sql);
    try{
      ResultSet res = this.statement.executeQuery();
      while(res.next()){
        String name = res.getString("name");
        String id = res.getString("id");
        String description = res.getString("description");
        String storeId = res.getString("storeId");
        int price = res.getInt("price");
        int placeOfShipment = res.getInt("placeOfShipment");
        String brand = res.getString("brand");
        String imgUrl = res.getString("imgUrl");
        set.add(new Goods(
          name == null || name == ""? "未知": name,
          id == null ||id == ""? "未知": id ,
          description == null ||description == ""? "未知": description,
          storeId == null || storeId == ""? "未知": storeId,
          placeOfShipment,
          brand == null || brand == ""? "未知":brand,
          imgUrl == null || imgUrl ==""? "未知": imgUrl,
          "",
          price
        ));
      }
      return set;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }finally {
      this.con.close();
    }
  }
}
