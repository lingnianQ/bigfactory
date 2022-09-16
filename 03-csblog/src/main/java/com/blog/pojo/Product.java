package com.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class Product {

    private Integer id;
    /**商品标题*/
    private String title;
    /**商品url*/
    private String url;
    /**商品价格*/
    private Double price;
    /**商品原价*/
    private Double oldPrice;
    /**商品浏览量*/
    private Integer viewCount; //浏览量
    /**商品销量*/
    private Integer saleCount;
    /**商品发布时间*/
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date created;  //发布时间  导包java.util
    /**商品分类*/
    private Integer categoryId; //商品分类id

}
