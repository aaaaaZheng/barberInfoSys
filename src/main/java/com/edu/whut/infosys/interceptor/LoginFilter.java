package com.edu.whut.infosys.interceptor;

import com.alibaba.fastjson.JSON;
import com.edu.whut.infosys.bean.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * @author aaaaaaa
 */
@Order(2)
@WebFilter(filterName = "loginFilter",urlPatterns = "/member")
public class LoginFilter implements Filter {
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/boss/login", "/boss", "/boss/password")));
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("执行filter-----------------------------------------------------------");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        HttpSession session = req.getSession();
        Object name = session.getAttribute("username");
        if(allowedPath){
            chain.doFilter(req, rep);
        }else{
            if(name==null){
                //设置允许跨域的配置
                // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
                rep.setHeader("Access-Control-Allow-Origin", "*");
                // 允许的访问方法
                rep.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
                // Access-Control-Max-Age 用于 CORS 相关配置的缓存
                rep.setHeader("Access-Control-Max-Age", "3600");
                rep.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");

                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                Result result = new Result();
                result.setMessage("请登录");
                OutputStreamWriter osw =new OutputStreamWriter(response.getOutputStream(),"UTF-8");
                PrintWriter writer = new PrintWriter(osw, true);
                String s = JSON.toJSONString(result);
                writer.write(s);
                writer.flush();
                writer.close();
                osw.close();
            }else{
                System.out.println("用户："+name+"   访问了："+((HttpServletRequest) request).getServletPath());
                chain.doFilter(req, rep);
            }
        }
    }

    @Override
    public void destroy() {
    }
}