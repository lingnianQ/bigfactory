package com.spring.dao;

import com.spring.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDao {

    int insert(Article article);

    Article selectById(Long id);

    List<Article> list();
}
