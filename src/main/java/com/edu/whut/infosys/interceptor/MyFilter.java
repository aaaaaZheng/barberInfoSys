package com.edu.whut.infosys.interceptor;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "myFilter",urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("执行filter-----------------------------------------------------------");
        HttpSession session = ((HttpServletRequest)request).getSession();
        Object isLogin1 = session.getAttribute("isLogin");
        if(isLogin1==null){
            request.getRequestDispatcher("/boss/login").forward(request, response);
        }else{
            boolean isLogin = (boolean)session.getAttribute("isLogin");
            if(isLogin){
            }else{
                request.getRequestDispatcher("/boss/login").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
}