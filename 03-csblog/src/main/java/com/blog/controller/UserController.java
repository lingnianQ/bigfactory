package com.blog.controller;

import com.blog.mapper.UserMapper;
import com.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/user/login")
    public int doLogin(@RequestBody User user,HttpSession session){
        //1.基于用户名查询用户信息
        User loginUser = userMapper.selectUserByUsername(user.getUsername());
        if(loginUser==null)return 2;//2 表示用户不存在
        //2.用户存在则比对密码是否正确
        if(!loginUser.getPassword().equals(
                DigestUtils.md5DigestAsHex(user.getPassword().getBytes())))
            return 3;//1 表示密码不正确
        session.setAttribute("loginUser", loginUser);
        return 1;//3 表示登陆ok
    }

    /**
     * 从session中获取当前登陆用户
     * @param session
     * @return
     */
    @GetMapping("/user/currentUser")
    public User currentUser(HttpSession session){
        return (User)session.getAttribute("loginUser");
    }

    /**
     * 执行登出逻辑，此时会从session中移除用户信息
     * @param session
     */
    @GetMapping("/user/logout")
    public void logout(HttpSession session){
        session.removeAttribute("loginUser");
    }

}
