package com.blog.controller;

import com.blog.annotation.RequiredLog;
import com.blog.mapper.ProductMapper;
import com.blog.pojo.Product;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
public  class ProductController {
    @Autowired
    private ProductMapper productMapper;

    /**查询前端系统首页商品列表信息*/
    @GetMapping("/product/list/index")
    public List<Product> doSelectIndex(){
        return productMapper.selectIndex();
    }

    /**查询商品销量并在前端系统中显示*/
    @GetMapping("/product/list/top")
    public List<Product> doSelectTop(){
        List<Product> products = productMapper.selectTop();
        for(Product pro:products){
            String title=pro.getTitle().substring(0,3)+"...";
            pro.setTitle(title);
        }
        return products;
    }
    /**基于id查询某个商品*/
    @RequiredLog(operation = "查看商品详情")
    @GetMapping("/product/select/{id}")
    public Product doSelectById(@PathVariable("id") Integer id){
        return productMapper.selectById(id);
    }

    @GetMapping("/product/selectByWd/{keyWord}")
    public List<Product> doSelectByWd(@PathVariable("keyWord") String keyWord){
        return productMapper.selectByWd(keyWord);
    }

    /**
     * 基于商品分类id查询商品信息
     * @param categoryId
     * @return
     */
    @GetMapping("/product/selectByCid/{categoryId}")
    public List<Product> doSelectByCid(@PathVariable("categoryId") Integer categoryId){
        return productMapper.selectByCid(categoryId);
    }

    /**
     * 发布新商品
     * @param product
     * @return
     */
    @PostMapping("/product/insert")
    public String doCreate(@RequestBody Product product){
        product.setCreated(new Date());
        productMapper.insert(product);
        return "create ok";
    }
    //======================================================

    /**
     * 分页查询所有商品信息
     * @param pageCurrent
     * @return
     */
    @RequiredLog(operation = "分页查询商品信息")
    @GetMapping("/product/list/admin")
    public PageInfo<Product> doSelectAdmin(Integer pageCurrent){
       return  PageHelper.startPage(pageCurrent, 3)
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        productMapper.selectAdmin();
                    }
       });

    }

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    @DeleteMapping("/product/delete/{id}")
    public String doDeleteById(@PathVariable("id") Integer id){
        String imgUrl = productMapper.selectUrlById(id);
        productMapper.deleteById(id);
        File file = new File("d:/file", imgUrl);
        if(file.exists())file.delete();
        return "delete ok";
    }


}
