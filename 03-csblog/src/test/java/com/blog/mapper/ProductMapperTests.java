package com.blog.mapper;

import com.blog.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;

    @Test
    void testSelectIndex(){
        List<Product> list = productMapper.selectIndex();
        list.forEach(System.out::println);
    }
    @Test
    void testSelectTop(){
        List<Product> list = productMapper.selectTop();
        list.forEach(System.out::println);
    }

    @Test
    void testSelectByWd(){
        List<Product> list = productMapper.selectByWd("2022");
        list.forEach(System.out::println);
    }

    @Test
    void testSelectByCid(){

    }

}
