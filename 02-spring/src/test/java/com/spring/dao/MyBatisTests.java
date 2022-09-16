package com.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
/**
 * 在SpringBoot工程下的单元测试类的上面要有一个@SpringBootTest注解，并且
 * 这个测试类所在的包，应该与项目启动类的包结构一样或者在启动类所在包的子包结构中。
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTests {
    /**
     * @Autowired注解描述属性时，用于告诉spring框架，
     * 为这个属性注入一个值，其规则是：
     * 首先按类型查询对应的bean对象，假如类型相同的只有一个则直接注入。假如有多个
     * 我还需要按属性名字进行查找，名字有相同的并且只有一个，则直接注入。名字没有相同
     * 的或者有多个相同则直接抛出异常。
     */
    @Autowired
    private SqlSession sqlSession;

    @Test
    void testGetConnection(){
        Connection connection = sqlSession.getConnection();
        System.out.println(connection);
    }
}
