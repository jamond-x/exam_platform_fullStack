package api.question;

import DAO.QuestionOP;
import entity.Question;
import utils.ParseRequest;
import utils.Restful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "add", value = "/add")
public class add extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Question qs = ParseRequest.get(request, Question.class);
    String res = "";
    QuestionOP op = new QuestionOP();
    try{
      Question question = op.add(qs);
      if(question == null){
        res = Restful.RestfulJson(Restful.CODE_ONE,"添加失败", null);
      }else {
        res = Restful.RestfulJson(Restful.CODE_ZERO,"添加成功！",question);
      }
    }catch (Exception e){
      e.printStackTrace();
      response.setStatus(500);
    }
    response.getWriter().write(res);
  }
}
