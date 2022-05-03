package api.question;

import DAO.QuestionOP;
import entity.Question;
import utils.ParseRequest;
import utils.Restful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashSet;

@WebServlet(name = "query-question/byType", value = "/query-question/byType")
public class queryquestionbyType extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Question qs = ParseRequest.get(request, Question.class);
    String res = "";
    QuestionOP op = new QuestionOP();
    try{
      HashSet<Question> resSet = op.queryByTypeId(String.valueOf(qs.getTypeId()));
      if(resSet != null){
        res = Restful.RestfulJson(Restful.CODE_ZERO,"查询成功！",resSet);
      }else{
        res = Restful.RestfulJson(Restful.CODE_ONE,"查询失败！",null);
      }
    }catch (Exception e){
      e.printStackTrace();
      response.setStatus(500);
    }
    response.getWriter().write(res);
  }
}
