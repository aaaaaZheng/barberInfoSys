package com.edu.whut.infosys.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author aaaaaaa
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle-----------------------------------------------------------");
        HttpSession session = request.getSession();
        Object isLogin1 = session.getAttribute("isLogin");
        if(isLogin1==null){
            request.getRequestDispatcher("/boss/login").forward(request, response);
            return false;
        }else{
            boolean isLogin = (boolean)session.getAttribute("isLogin");
            if(isLogin){
                return true;
            }else{
                request.getRequestDispatcher("/boss/login").forward(request, response);
                return false;
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
