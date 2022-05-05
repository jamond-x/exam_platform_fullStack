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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "Verify")
public class Verify implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
      System.out.println("filter Verify");
      HttpServletRequest rq = (HttpServletRequest) request;
      HttpServletResponse res = (HttpServletResponse) response;
      String url = rq.getRequestURI();
      HashSet<String> urlSet = new HashSet<String>();
      urlSet.add("/api/login");     // 只给这几个接口放行  其余的需要token并且有效
      urlSet.add("/api/register");
      urlSet.add("/api/tokenverify");

      if(!urlSet.contains(url)){
        String token = rq.getHeader("token");
        System.out.println(token);
        if(!ProcessToken.verifyToken(token)){
          res.setStatus(401);
          res.getWriter().write(Restful.RestfulJson(Restful.CODE_ONE, "token失效，请重新登录！",null));
//          return;
        }
      }
      chain.doFilter(rq, res);
    }
}
