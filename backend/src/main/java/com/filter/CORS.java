package com.filter;

import utils.Restful;
import utils.token.ProcessToken;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CORS")
public class CORS implements Filter {
  public void init(FilterConfig config) throws ServletException {
  }

  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
    System.out.println("filter CORS");
//    HttpServletResponse res = (HttpServletResponse) response;
//    res.setHeader("Access-Control-Allow-Headers", "*");
//    res.setHeader("Access-Control-Allow-Method", "*");
//    //目前开发环境允许所有的域
//    res.setHeader("Access-Control-Allow-Origin","*");
//    res.setContentType("application/json;charset=utf-8");
//    chain.doFilter(request, res);

    HttpServletResponse response1 = (HttpServletResponse) response;
    HttpServletRequest request1 = (HttpServletRequest) request;


    String token = request1.getHeader("token");
    if(!ProcessToken.verifyToken(token)){
      response1.setStatus(401);
      response1.getWriter().write(Restful.RestfulJson(Restful.CODE_ONE, "token失效，请重新登录！",null));
    }

    response.setContentType("text/html;charset=UTF-8");
    response1.setHeader("Access-Control-Allow-Origin", "*");
    response1.setHeader("Access-Control-Allow-Methods", "*");
    response1.setHeader("Access-Control-Max-Age", "3600");
    response1.setHeader("Access-Control-Allow-Headers", "Origin,No-Cache,x-requested-with,If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,Authorization,SessionToken,JSESSIONID,token");
    response1.setHeader("Access-Control-Allow-Credentials", "true");

    chain.doFilter(request1, response1);


  }
}
