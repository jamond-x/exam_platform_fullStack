package api.user;

import DAO.Operate;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Token;
import entity.User;
import utils.ParseRequest;
import utils.Restful;
import utils.token.ProcessToken;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "userInfo", value = "/userInfo")
public class userInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ParseRequest.get(request, User.class);
        Operate op = new Operate();
        String resJson = "";
        try {
            User res = op.getUserInfo(user.getId());
            if(res == null){
                resJson = Restful.RestfulJson(Restful.CODE_ONE,"查询出错了！",null);
                response.setStatus(500);
            }else {
                resJson = Restful.RestfulJson(Restful.CODE_ZERO,"查询成功！",res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(resJson);
    }
}
