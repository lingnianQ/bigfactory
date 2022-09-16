package com.blog.controller;

import com.blog.cache.CacheConstants;
import com.blog.mapper.CategoryMapper;
import com.blog.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * 查询分类列表
     *  @Cacheable注解描述的方法是一个缓存切入点方法，
     *  查询时，可以优先从缓存取数据。缓存中没有则查询数据库
     * @return
     */
    @Cacheable(value = CacheConstants.CAT_LIST,sync = true)
    @GetMapping("/category/list")
    public List<Category> list(){
        System.out.println("===list()===");
        List<Category> list = categoryMapper.list();
        return list;
    }

    /**
     * 创建新分类
     * @param category
     */
    @PostMapping("/category/insert")
    public void doCreate(@RequestBody Category category){
          categoryMapper.insert(category);
    }

    /**
     * 基于id删除分类
     * @param id
     */
    @Caching(evict = {
            @CacheEvict(value = CacheConstants.CAT_LIST, allEntries = true)
    })
    @DeleteMapping("/category/delete/{id}")
    public void doDeleteById(@PathVariable("id") Integer id){
        categoryMapper.deleteById(id);
    }
}
