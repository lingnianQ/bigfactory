package com.blog.controller;

import com.blog.mapper.BannerMapper;
import com.blog.pojo.Banner;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
public class BannerController {
    @Autowired
    private BannerMapper bannerMapper;

    @GetMapping("/banner/list")
    public List<Banner> doList(){
        return bannerMapper.list();
    }

    @Select("/banner/{id}")
    public String doSelectById(@PathVariable("id") Integer id){
        return bannerMapper.selectUrlById(id);
    }

    @PostMapping("/banner/insert")
    public String doCreate(@RequestBody Banner banner){
        bannerMapper.insert(banner);
        return "create ok";
    }

    @DeleteMapping("/banner/delete/{id}")
    public String doDeleteById(@PathVariable("id") Integer id){
        String url = bannerMapper.selectUrlById(id);
        bannerMapper.deleteById(id);
        File file=new File("d:/file"+url);
        if(file.exists())file.delete();
        return "delete ok";
    }
}
