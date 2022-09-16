package com.spring.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleTagDao {
    /**写入文章和标签的关系数据*/
    int insert(@Param("articleId") Long articleId,
               @Param("tagIds") Integer[] tagIds);
}
