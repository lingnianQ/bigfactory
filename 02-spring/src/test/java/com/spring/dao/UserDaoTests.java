package com.spring.dao;

import com.spring.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.List;

/**
 * 在SpringBoot工程下的单元测试类的上面要有一个@SpringBootTest注解，并且
 * 这个测试类所在的包，应该与项目启动类的包结构一样或者在启动类所在包的子包结构中。
 *
 */

@SpringBootTest
public class UserDaoTests {

    @Autowired
    private UserDao userDao;//底层指向的是一个代理对象

    @Test
    void testInsert(){
        User user=new User();
        user.setUsername("Pony");
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        user.setNickname("Pony");
        user.setMobile("1391112121");
        user.setStatus(1);
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());
        userDao.insert(user);//com.spring.dao.UserDao.insert
    }
    @Test
    void testSelectByUsername(){
        User user = userDao.selectByUsername("Jack");
        System.out.println(user);
    }

    @Test
    void testList(){
        List<User> list = userDao.list("2020/12/11");
//      for(User user:list){
//            System.out.println(user);
//      }
        list.forEach(System.out::println);
    }
    @Test
    void testValidById(){
        Long[] ids={1L,3L};
        int rows=userDao.validById(ids,1);
        System.out.println(rows);
    }

    @Test
    void testUpdate(){
        User user = userDao.selectByUsername("Pony");
        user.setNickname("Pony123");
        user.setMobile("11122222235");
        user.setModifiedTime(new Date());
        userDao.update(user);
    }

}
