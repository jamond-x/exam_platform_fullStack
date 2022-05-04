package api.user;

import DAO.Operate;
import entity.User;
import utils.Restful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashSet;

@WebServlet(name = "all-users", value = "/all-users")
public class allusers extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String res = "";
    try{
        Operate op = new Operate();
        HashSet<User> resData = op.allUsers();
        if(resData != null){
          res = Restful.RestfulJson(Restful.CODE_ZERO,"查询成功！",resData);
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
