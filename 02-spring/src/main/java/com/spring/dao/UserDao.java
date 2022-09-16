package com.spring.dao;

import com.spring.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户模块的数据访问层接口，
 * @Mapper 注解由mybatis提供，用于告诉底层请为这个类创建一个具体的实现类，
 * 在实现类中定义具体的数据访问操作
 */
@Mapper
@Repository
public interface UserDao {
    /**添加新的User信息*/
    int insert(User user);

    /**基于创建时间(注册时间)查询用户信息*/
    List<User> list(String createdTime);
    /**
     * 修改用户状态
     * @param ids 用户id
     * @param status 用户状态
     * @return
     */
    int validById(@Param("ids") Long[] ids,
                  @Param("status") Integer status);

    /**更新用户信息*/
    int update(User user);

    /**基于用户名查询用户信息*/
   @Select(" select id,username,password,nickname,mobile,status " +
            "  from tb_users " +
            "  where username=#{username}")
    User selectByUsername(String username);

}
