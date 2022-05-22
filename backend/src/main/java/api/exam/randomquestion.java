package api.exam;

import DAO.Operate;
import DAO.QuestionOP;
import entity.Question;
import entity.User;
import utils.Count;
import utils.ParseRequest;
import utils.Restful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashSet;

@WebServlet(name = "random-question", value = "/random-question.js")
public class randomquestion extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    Count count = ParseRequest.get(request, Count.class);
//    String resJson = "";
//    try{
//      QuestionOP op = new QuestionOP();
//      HashSet<Question> res = op.randomQuestion(count.getCount());
//      if(res != null){
//        resJson = Restful.RestfulJson(Restful.CODE_ZERO,"出题成功！",res);
//      }else{
//        resJson = Restful.RestfulJson(Restful.CODE_ONE,"出题失败！",null);
//      }
//    }catch (Exception e){
//      e.printStackTrace();
//      response.setStatus(500);
//    }
//    response.getWriter().write(resJson);


    String resJson = "";
    resJson = Restful.RestfulJson(Restful.CODE_ZERO,"成功！",new User("10086","密码","huengemail@163.com"));
    response.getWriter().write(resJson);

  }
}

