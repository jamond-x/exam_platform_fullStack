package api.question;

import DAO.QuestionOP;
import entity.Question;
import utils.ParseRequest;
import utils.Restful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "delete", value = "/delete")
public class delete extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Question question = ParseRequest.get(request, Question.class);
    String res = "";
    try{
      QuestionOP op = new QuestionOP();
      if(op.delete(question.getId())){
        res = Restful.RestfulJson(Restful.CODE_ZERO,"删除成功！",null);
      }else {
        res = Restful.RestfulJson(Restful.CODE_ONE,"删除失败", null);
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    response.getWriter().write(res);
  }
}
