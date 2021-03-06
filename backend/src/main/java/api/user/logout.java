package api.user;

import DAO.Operate;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import utils.ParseRequest;
import utils.Restful;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "logout", value = "/logout")
public class logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ParseRequest.get(request, User.class);
        Operate op = new Operate();
        String resJson = "";
        try{
            if(op.updateToken(user.getId(),"",false)){
                resJson = Restful.RestfulJson(Restful.CODE_ZERO, "token已删除", null);

            }else{
                resJson = Restful.RestfulJson(Restful.CODE_ONE, "出错啦，再试一次！", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(resJson);
    }
}
