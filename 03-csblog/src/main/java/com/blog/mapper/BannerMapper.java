package com.blog.mapper;

import com.blog.pojo.Banner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Select("select id,url from banner")
    List<Banner> list();

    @Select("select url from banner where id=#{id}")
    String selectUrlById(Integer id);

    @Insert("insert into banner (id,url) values (null,#{url})")
    int insert(Banner banner);

    @Delete("delete from banner where id=#{id}")
    int deleteById(Integer id);

}
