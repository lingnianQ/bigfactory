package com.spring.dao;

import com.spring.pojo.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class ArticleDaoTests {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleTagDao articleTagDao;

    @Test
    void insert(){
     Article article=new Article();
     article.setTitle("Spring Boot");
     article.setContent("Very Good");
     article.setType("1");
     article.setStatus("1");
     article.setUserId(1L);
     article.setCreatedTime(new Date());
     article.setModifiedTime(new Date());
     //将文章自身信息写入到数据库
     System.out.println("article.insert.before.id="+article.getId());
     articleDao.insert(article);
     System.out.println("article.insert.after.id="+article.getId());
     //将文章和标签关系数据写入到数据
     articleTagDao.insert(article.getId(),new Integer[]{1,3});
    }

    @Test
    void testSelectById(){
        Article article = articleDao.selectById(3L);
        System.out.println(article);
    }
    @Test
    void testList(){
        List<Article> list = articleDao.list();
        list.forEach(System.out::println);
    }
}
