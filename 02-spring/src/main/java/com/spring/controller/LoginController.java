package com.spring.controller;

import com.spring.dao.UserDao;
import com.spring.pojo.User;
import com.spring.pojo.dto.LoginDTO;
import com.spring.pojo.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

//@Controller
//@ResponseBody
@RestController
public class LoginController {

    @Autowired
    private UserDao userDao;

    //登陆请求参数方案1
    //POST http://localhost:8080/login01/jack/123456
    @PostMapping("/login01/{username}/{password}")
    public JsonResult doLogin(@PathVariable("username") String username,
                              @PathVariable("password") String password,
                              HttpSession session){
        //1.基于用户名查询用户信息
        User user = userDao.selectByUsername(username);
        //2.判断用户是否存在
        if(user==null)
            return new JsonResult(401,"user is not exist");
        //3.判断密码是否正确
        if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes())))
            return new JsonResult(401,"password is error");
        //4.存储用户信息
        session.setAttribute("loginUser",user);
        return new JsonResult(200,"login ok");
    }
    //登陆请求方案2
     @PostMapping("/login02")
    public JsonResult doLogin(@RequestBody LoginDTO loginDTO){
        //1.基于用户名查询用户信息
        User user = userDao.selectByUsername(loginDTO.getUsername());
        //2.判断用户是否存在
        if(user==null)
            return new JsonResult(401,"user is not exist");
        //3.判断密码是否正确
        if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(
                loginDTO.getPassword().getBytes())))
            return new JsonResult(401,"password is error");
        //4.存储用户信息
         ServletRequestAttributes requestAttributes=
                 (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
         HttpSession session=requestAttributes.getRequest().getSession();
         session.setAttribute("loginUser",user);
        return new JsonResult(200,"login ok");
    }

    @GetMapping("/logout")
    public JsonResult doLogout(HttpSession session){
        session.removeAttribute("loginUser");
        return new JsonResult(200, "logout ok");
    }
}
