package com.blog.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 判断用户是否登陆的过滤器
 */
@WebFilter(filterName = "loginFilter",
           urlPatterns = {"/admin.html","/insertBanner.html","/insertProduct.html"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        //没有登陆则跳转到登陆页面
        if(loginUser==null){
            //重定向到登陆页面
            response.sendRedirect("/login.html");
            return;
        }
        //登陆ok，继续执行后续操作
        filterChain.doFilter(request, response);
    }
}
