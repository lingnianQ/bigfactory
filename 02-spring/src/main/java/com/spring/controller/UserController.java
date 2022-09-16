package com.spring.controller;

import com.spring.dao.UserDao;
import com.spring.pojo.User;
import com.spring.pojo.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    private  UserDao userDao; //DI 依赖注入

//    @Autowired
//    public UserController(UserDao userDao){
//        this.userDao=userDao;
//    }

//    @Autowired
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @PostMapping("/register")
    public JsonResult doRegister(@RequestBody User user){
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userDao.insert(user);
        return new JsonResult(200,"register ok");
    }
    //http://localhost/user/list?createdTime=2020-12-10
    //@GetMapping("/user/list")
    @GetMapping("/user/list/{createdTime}")
    public JsonResult doList(@PathVariable("createdTime") String createdTime){
        List<User> list = userDao.list(createdTime);
        return new JsonResult(list);
    }
    /**
     * 修改用户状态( @PatchMapping一般用于处理少量数据的更新请求)
     * @param ids
     * @param status
     * @return
     * GET http://localhost:8080/user/valid/1,3/1
     */
    @PatchMapping("/user/valid/{ids}/{status}")
    public JsonResult doValidById(@PathVariable("ids") Long[] ids,
                                  @PathVariable("status") Integer status){
        userDao.validById(ids, status);
        return new JsonResult("update ok");
    }

    @PutMapping("/user/update")
    public JsonResult doUpdate(@RequestBody User user){
        user.setModifiedTime(new Date());
        userDao.update(user);
        return new JsonResult("update ok");
    }
}
