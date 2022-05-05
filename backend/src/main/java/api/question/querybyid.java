package api.question;

import DAO.QuestionOP;
import entity.Question;
import utils.ParseRequest;
import utils.Restful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "query-all-id", value = "/admin/query-by-id")
public class querybyid extends HttpServlet {
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
      Question question = op.queryById(qs.getId());
      System.out.println(question.toString());
      if(question == null){
        res = Restful.RestfulJson(Restful.CODE_ONE,"查询失败", null);
      }else {
        res = Restful.RestfulJson(Restful.CODE_ZERO,"查询成功！",question);
      }
    }catch (Exception e){
      e.printStackTrace();
      response.setStatus(500);
    }
    response.getWriter().write(res);
  }
}
