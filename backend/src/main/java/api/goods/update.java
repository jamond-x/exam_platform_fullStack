package api.goods;


import entity.Goods;
import service.goods.updateSv;
import utils.ParseRequest;
import utils.Restful;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "goods/update", value = "/goods/update")
public class update extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    Goods goods = ParseRequest.get(request, Goods.class);
    String resJson = "";
    Goods res = null;
    try {
      res = updateSv.updateGoods(goods);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if(res != null){
      resJson = Restful.RestfulJson(Restful.CODE_ZERO,"更新成功！",res);
    }else{
      resJson = Restful.RestfulJson(Restful.CODE_ONE,"更新失败！",null);
    }
    response.getWriter().write(resJson);
  }
}
