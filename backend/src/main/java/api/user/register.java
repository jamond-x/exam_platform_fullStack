package api.user;

import DAO.Operate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
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

@WebServlet(name = "register", value = "/api/register")
public class register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ParseRequest.get(request, User.class);
        String pwMd5 =  Hashing.md5().hashBytes(user.getPassword().getBytes("UTF-8")).toString();
        Operate op = new Operate();
        String resJson = "";
        try {
            boolean res = op.signup(user.getId(), pwMd5, user.getEmail());
                String jwt =  ProcessToken.dispatchToken(user.getId(), ProcessToken.minute*1); // token 开发时期默认1分钟过期
                resJson = Restful.RestfulJson(Restful.CODE_ZERO,"注册成功！",new Token(jwt,user.getId()));  // 给token
                op.updateToken(user.getId(),jwt,true);
        } catch (Exception e) {
            e.printStackTrace();
//            resJson = Restful.RestfulJson(Restful.CODE_ONE,"注册失败！",new Token("null","null"));
        }
        response.getWriter().write(resJson);
    }
}
