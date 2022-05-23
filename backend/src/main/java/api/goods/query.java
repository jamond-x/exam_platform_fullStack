package api.goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Goods;
import service.goods.addSv;
import service.goods.querySv;
import utils.ParseRequest;
import utils.Restful;

import java.io.IOException;
import java.util.HashSet;

@WebServlet(name = "goods/query", value = "/goods/query")
public class query extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String resJson = "";
    HashSet<Goods> res = querySv.queryGoods();
    if(res != null){
      resJson = Restful.RestfulJson(Restful.CODE_ZERO,"查询成功！",res);
    }else{
      resJson = Restful.RestfulJson(Restful.CODE_ONE,"查询失败！",null);
    }
    response.getWriter().write(resJson);
  }
}
