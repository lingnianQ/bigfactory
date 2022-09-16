package com.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JsonController {

    @GetMapping("/json")
    public void json() throws IOException {
        //String json="{\"id\":100,\"name\":\"jack\"}";

        Map<String,Object> map=new HashMap();
        map.put("id", 100);
        map.put("name", "Jack");
        map.put("phone", "1111111111");

        //将对象转换为json格式的字符串
        String json=
        new ObjectMapper().writeValueAsString(map);
        System.out.println("json="+json);

        //将json格式数据响应到客户端(Response-借助输出流->json)
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes)
        RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.flush();


    }
}
