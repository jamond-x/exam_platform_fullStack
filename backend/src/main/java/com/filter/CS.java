package com.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CS implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        HttpServletResponse resp=(HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Headers", "*");
        //允许所有的方法访问  如post , get方法
        resp.setHeader("Access-Control-Allow-Method", "*");
        //目前开发环境允许所有的域
        resp.setHeader("Access-Control-Allow-Origin","*");
        chain.doFilter(request, resp);
    }

    @Override
    public void destroy() {
    }
}
