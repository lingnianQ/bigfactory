package com.blog.mapper;

import com.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select id,username,password,nick from user where username=#{username}")
    User selectUserByUsername(String username);
}
