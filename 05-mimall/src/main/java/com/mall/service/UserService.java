package com.mall.service;

import com.mall.pojo.User;

public interface UserService {

    /**
     * 注册
     */
    void register(User user);

    /**
     * 登录
     */
    User login(String username, String password);
}
