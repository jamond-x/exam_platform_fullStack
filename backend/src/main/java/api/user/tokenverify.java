package api.user;

import DAO.Operate;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Token;
import entity.User;
import utils.Restful;
import utils.token.ProcessToken;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "token/verify", value = "/token/verify")
public class tokenverify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String line = null;
        String json = "";
        while ((line = reader.readLine()) != null){
            json += line;
        }
        ObjectMapper om = new ObjectMapper();
        Token token = om.readValue(json, Token.class);
        Operate op = new Operate();
        String resJson = "";
        try {
            boolean res = op.verifyToken(token);
            if(res){ // 有效  返回id
                String id = ProcessToken.parseToken(token.getToken());
                resJson = Restful.RestfulJson(Restful.CODE_ZERO,"token验证成功！",new User(id));
            }else{
                resJson = Restful.RestfulJson(Restful.CODE_ONE,"token失效！",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(resJson);
    }
}
