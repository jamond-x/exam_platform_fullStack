package DAO;

import database.DBPool;
import entity.Question;
import entity.QuestionType;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class QuestionOP implements APIQuestion {
  private DBPool dbInstance = null;
  private Connection con = null;
  private PreparedStatement statement = null;
  public QuestionOP(){
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
  public Question queryById(String id) throws Exception {
    init();
    String sql = "SELECT a.*,b.name FROM `questionbank` a, `questiontype` b WHERE a.id=? AND a.typeId=b.id";

    this.statement = this.con.prepareStatement(sql);

    this.statement.setString(1,id);
    try{
      ResultSet res = this.statement.executeQuery();
      Question question = null;
      if(res.next()){
        question = new Question(
          res.getString("id"),
          Integer.parseInt(res.getString("typeId")),
          res.getString("description"),
          res.getString("optionA"),
          res.getString("optionB"),
          res.getString("optionC"),
          res.getString("optionD"),
          res.getString("answer"),
          res.getString("name")
        );
      }
      return question == null ? null:question;
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      this.con.close();
    }
    return null;
  }

  @Override
  public HashSet<Question> queryByTypeId(String id) throws Exception {
    init();
    String sql = "SELECT a.*,b.name FROM `questionbank` a, `questiontype` b WHERE a.typeId=? AND a.typeId=b.id";
    this.statement = this.con.prepareStatement(sql);
    this.statement.setString(1,id);
    try{
      ResultSet res = this.statement.executeQuery();
      HashSet<Question> questionSet = new HashSet<Question>();
      while(res.next()){
        questionSet.add(new Question(
          res.getString("id"),
          Integer.parseInt(res.getString("typeId")),
          res.getString("description"),
          res.getString("optionA"),
          res.getString("optionB"),
          res.getString("optionC"),
          res.getString("optionD"),
          res.getString("answer"),
          res.getString("name")
        ));
      }
      return questionSet == null ? null:questionSet;
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      this.con.close();
    }
    return null;
  }

  @Override
  public HashSet<QuestionType> queryAllTypes() throws Exception {
    init();
    String sql = "SELECT * FROM `questiontype`";
    this.statement = this.con.prepareStatement(sql);
    try{
      ResultSet res = this.statement.executeQuery();
      HashSet<QuestionType> questionSet = new HashSet<QuestionType>();
      while(res.next()){
        questionSet.add(new QuestionType(
          res.getString("id"),
          res.getString("name"),
          res.getString("description")
        ));
      }
      System.out.println(questionSet);
      return questionSet == null ? null:questionSet;
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      this.con.close();
    }
    return null;
  }

  @Override
  public Question add(Question question) throws Exception {
    init();
//    String sql = "INSERT INTO `questionbank`  VALUES('100009', '测试题目', '107', '选项A', '选项B', '选项C', '选项D', 'A')";
    String sql = "INSERT INTO questionbank(id,description,typeId,optionA,optionB,optionC,optionD,answer)"+  "  VALUES (?, ?, ?, ?, ?, ?, ?,?)";
    try{
      this.statement = this.con.prepareStatement(sql);
      this.statement.setString(1,question.getId());
      this.statement.setString(2,question.getDescription());
      this.statement.setString(3,String.valueOf(question.getTypeId()));
      this.statement.setString(4,question.getOptionA());
      this.statement.setString(5,question.getOptionB());
      this.statement.setString(6,question.getOptionC());
      this.statement.setString(7,question.getOptionD());
      this.statement.setString(8,question.getAnswer());
      System.out.println(sql);
      boolean res =  this.statement.execute();
      if(!res){
        return question;
      }
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }finally {
      this.con.close();
    }
    return null;
  }
}
