package com.spring.dao;

import com.spring.pojo.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagDao {

    @Select("select * from tb_tags")
    List<Tag> list();

    @Insert("insert into tb_tags (name,remark,created_time,modified_time) " +
            "values (#{name},#{remark},#{createdTime},#{modifiedTime})")
    int insert(Tag tag);

    @Update("update tb_tags set name=#{name},remark=#{remark},modified_time=#{modifiedTime} " +
            "where id=#{id}")
    int update(Tag tag);

    @Delete("delete from tb_tags where id=#{id}")
    int deleteById(Long id);
}
