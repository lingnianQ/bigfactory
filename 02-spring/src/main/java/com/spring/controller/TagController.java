package com.spring.controller;

import com.spring.dao.TagDao;
import com.spring.pojo.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    @Autowired
    private TagDao tagDao;

    @GetMapping("/tag")
    public JsonResult list(){
        return new JsonResult(tagDao.list());
    }
}
