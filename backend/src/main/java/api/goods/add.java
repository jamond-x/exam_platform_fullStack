package api.goods;

import service.goods.addSv;
import entity.Goods;
import utils.ParseRequest;
import utils.Restful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "goods/add", value = "/goods/add")
public class add extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Goods goods = ParseRequest.get(request, Goods.class);
    String resJson = "";

    if(addSv.addGood(goods)){
      resJson = Restful.RestfulJson(Restful.CODE_ZERO,"添加成功！",null);
    }else{
      resJson = Restful.RestfulJson(Restful.CODE_ONE,"添加失败！",null);
    }
    response.getWriter().write(resJson);
  }
}
