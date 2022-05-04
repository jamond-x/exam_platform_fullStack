package DAO;

import entity.Question;
import entity.QuestionType;

import java.util.Arrays;
import java.util.HashSet;

public interface APIQuestion {
  public  Question queryById(String id) throws Exception;
  public HashSet<Question> queryByTypeId(String id) throws Exception;
  public HashSet<QuestionType> queryAllTypes()throws Exception;
  public Question add(Question question) throws Exception;
}
