package com.spring.dao;

import com.spring.pojo.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class TagDaoTests {
    @Autowired
    private TagDao tagDao;

    @Test
    void testInsert(){
        Tag tag=new Tag();
        tag.setName("Oracle");
        tag.setRemark("Oracle ...");
        tag.setCreatedTime(new Date());
        tag.setModifiedTime(new Date());
        tagDao.insert(tag);
    }
    @Test
    void testList(){
        List<Tag> list = tagDao.list();
        list.forEach(System.out::println);
    }

    @Test
    void testUpdate(){
       Tag tag=new Tag();
       tag.setId(1L);
       tag.setName("Redis");
       tag.setRemark("No SQL");
       tag.setModifiedTime(new Date());
       tagDao.update(tag);
    }
    @Test
    void testDelete(){
        tagDao.deleteById(2L);
    }

}
