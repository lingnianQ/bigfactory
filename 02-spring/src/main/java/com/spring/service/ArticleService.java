package com.spring.service;

import com.spring.pojo.Article;

import java.util.List;

public interface ArticleService {

     void insert(Article article);
     Article selectById(Long id);
     List<Article> list();
}
