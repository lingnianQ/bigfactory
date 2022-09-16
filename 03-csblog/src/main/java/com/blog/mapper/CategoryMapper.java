package com.blog.mapper;

import com.blog.pojo.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 查询所有分类
     * @return
     */
    @Select("select id,name from category")
    List<Category> list();

    /**
     * 添加新分类
     * @param category
     * @return
     */
    @Insert("insert into category (id,name) values (null,#{name})")
    int insert(Category category);


    /**
     * 基于id删除分类
     * @param id
     * @return
     */
    @Delete("delete from category where id=#{id}")
    int deleteById(Integer id);

}
