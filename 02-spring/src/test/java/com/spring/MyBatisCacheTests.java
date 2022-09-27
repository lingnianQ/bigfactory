package com.spring;

import com.spring.pojo.Article;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisCacheTests {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Test
    void testFirstLevelCache(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String statement="com.spring.dao.ArticleDao.selectById";
        Article art1=sqlSession.selectOne(statement,1);
        Article art2=sqlSession.selectOne(statement,1);//这里的数据来自1级缓存
        System.out.println(art1==art2);
        sqlSession.close();
    }
    @Test
    void testSecondLevelCache(){
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        String statement="com.spring.dao.ArticleDao.selectById";
        Article art1=sqlSession1.selectOne(statement,1);
        sqlSession1.close();
        Article art2=sqlSession2.selectOne(statement,1);//这里的数据来自1级缓存
        System.out.println(art1==art2);
        sqlSession2.close();
    }
}
