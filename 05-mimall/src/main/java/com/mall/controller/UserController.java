package com.mall.controller;
import com.mall.pojo.User;
import com.mall.pojo.dto.UserLoginDTO;
import com.mall.pojo.dto.UserRegisterDTO;
import com.mall.pojo.vo.JsonResult;
import com.mall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public JsonResult register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        userService.register(user);
        return new JsonResult("register ok");
    }

    @PostMapping("/user/login")
    public JsonResult login(@Valid @RequestBody UserLoginDTO userLoginDTO,
                                  HttpSession session) {
        User user=userService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        //设置Session
        session.setAttribute("loginUser", user);
        return new JsonResult(user);
    }

    @GetMapping("/user")
    public JsonResult userInfo(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new JsonResult(user);
    }
    @PostMapping("/user/logout")
    public JsonResult logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return new JsonResult("logout ok");
    }
}
