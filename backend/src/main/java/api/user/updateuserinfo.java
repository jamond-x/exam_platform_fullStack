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

@WebServlet(name = "update-user-info", value = "/update-user-info")
public class updateuserinfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ParseRequest.get(request, User.class);
        Operate op = new Operate();
        String resJson = "";
        try {
            User res = op.updateUserInfo(user);
            if(res == null){
                resJson = Restful.RestfulJson(Restful.CODE_ONE,"更新出错了！",null);
                response.setStatus(500);
            }else {
                resJson = Restful.RestfulJson(Restful.CODE_ZERO,"更新成功！",res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(resJson);
    }
}
