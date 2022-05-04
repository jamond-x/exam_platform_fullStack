package api.user;

import DAO.Operate;
import com.google.common.hash.Hashing;
import entity.Token;
import entity.User;
import utils.ParseRequest;
import utils.Restful;
import utils.token.ProcessToken;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = ParseRequest.get(request, User.class);
        Operate op = new Operate();
        String resJson = "";
        String pwMd5 =  Hashing.md5().hashBytes(user.getPassword().getBytes("UTF-8")).toString();
      System.out.println(pwMd5);
        try {
            boolean res = op.login(user.getId(), pwMd5);
            if(res){
                String jwt =  ProcessToken.dispatchToken(user.getId(), ProcessToken.minute*300); // token 开发时期默认1分钟过期
                resJson = Restful.RestfulJson(Restful.CODE_ZERO,"登录成功！",new Token(jwt,user.getId()));  // 给token
                op.updateToken(user.getId(),jwt,true);
            }else {
                  resJson = Restful.RestfulJson(Restful.CODE_ONE,"账号或密码错误！",new Token("null","null"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(resJson);
    }
}
