package com.filter;


import DAO.Operate;
import utils.Restful;
import utils.token.ProcessToken;

import java.io.IOException;
import java.util.HashSet;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authentication implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        request.setCharacterEncoding("UTF-8");
        System.out.println("filter Authentication");
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        rq.setCharacterEncoding("UTF-8");


        String url = rq.getRequestURI();
        System.out.println(url);
//        HashSet<String> urlSet = new HashSet<String>();
//        urlSet.add("/api/login");     // 只给这几个接口放行  其余的需要token并且有效
//        urlSet.add("/api/register");
//        urlSet.add("/api/tokenverify");

        HashSet<String> adminUrl = new HashSet<String>();
        adminUrl.add("/api/admin/all-users");
        adminUrl.add("/api/admin/add");
        adminUrl.add("/api/admin/delete");
        adminUrl.add("/api/admin/query-all-types");
        adminUrl.add("/api/admin/query-by-type");

//      if(!urlSet.contains(url)){
          String token = rq.getHeader("token");
          System.out.println(token);

          if(!ProcessToken.verifyToken(token)){
            res.setHeader("Access-Control-Allow-Origin","*");
//            res.setStatus(401);
//            res.getWriter().write(Restful.RestfulJson(Restful.CODE_ONE, "token失效，请重新登录！",null));
//            return;
          }
//
//          if(adminUrl.contains(url)){
//             String id = ProcessToken.parseToken(token);
//            Operate op = new Operate();
//            try {
//              if(!op.isAdmin(id)){
//                res.setStatus(403);
//                res.getWriter().write(Restful.RestfulJson(Restful.CODE_ONE, "您不是管理员，无权访问！",null));
////                return;
//              }
//            } catch (Exception e) {
//              e.printStackTrace();
//            }
//          }

//        }


//        res.setHeader("Access-Control-Allow-Headers", "*");
//        res.setHeader("Access-Control-Allow-Method", "*");
//        //目前开发环境允许所有的域
//        res.setContentType("application/json;charset=utf-8");
      chain.doFilter(rq, res);
    }

    @Override
    public void destroy() {
    }
}
