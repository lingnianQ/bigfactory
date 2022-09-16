package com.mall.service.impl;

import com.mall.dao.UserMapper;
import com.mall.pojo.User;
import com.mall.service.UserService;
import com.mall.service.exp.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void register(User user) {
        //username不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if (countByUsername > 0)
            throw new ServiceException("用户已存在");
        //email不能重复
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if (countByEmail > 0)
            throw new ServiceException("email不能重复");
        //MD5摘要算法(Spring自带)
        user.setPassword(DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)
        ));
        //写入数据库
        userMapper.insertSelective(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null)
            throw new ServiceException("用户或密码不正确");
        if (!user.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8))))
            throw new ServiceException("用户或密码不正确");
        user.setPassword("");

        return user;
    }

}
