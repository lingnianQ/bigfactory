package com.spring.controller;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

public class TimeAccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("==preHandler==");
        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        if(hour<6||hour>=23)
            throw new RuntimeException("请在指定时间访问6~13");
        return true;//false表示请求到此结束，true表示将请求继续传递
    }
}
