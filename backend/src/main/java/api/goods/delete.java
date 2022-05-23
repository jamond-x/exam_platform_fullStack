package api.goods;


import entity.Goods;
import service.goods.deleteSv;
import utils.ParseRequest;
import utils.Restful;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "goods/delete", value = "/goods/delete")
public class delete extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Goods goods = ParseRequest.get(request, Goods.class);
    String resJson = "";

    if(deleteSv.deleteGood(goods.getId())){
      resJson = Restful.RestfulJson(Restful.CODE_ZERO,"删除成功！",null);
    }else{
      resJson = Restful.RestfulJson(Restful.CODE_ONE,"删除失败！",null);
    }
    response.getWriter().write(resJson);
  }
}
