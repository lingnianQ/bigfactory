package com.blog.mapper;

import com.blog.pojo.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 查询前端首页需要的商品列表信息
     * @return
     */
    @Select("select id,title,url,price,old_price,view_count,sale_count " +
            " from product")
    //@Result(property = "oldPrice",column = "old_price")
    //@Result(property = "viewCount",column = "view_count")
    //@Result(property = "saleCount",column = "sale_count")
    List<Product> selectIndex();

    /**
     * 查询前端系统需要的商品销量榜
     * @return
     */
    @Select("select id,title,url,price,old_price,view_count,sale_count " +
            " from product " +
            " order by sale_count desc " +
            " limit 6")
    List<Product> selectTop();

    /**
     * 基于id查询某个商品的商品详情。
     * @param id
     * @return
     */
    @Select("select id,title,url,price,old_price,view_count,sale_count " +
            " from product " +
            " where id=#{id} ")
    Product selectById(Integer id);

    /**
     * 基于页面上输入的关键字查询商品信息
     * @param keyWord
     * @return
     */
    @Select("select id,title,url,price,old_price,view_count,sale_count " +
            " from product " +
            " where title like concat('%',#{keyWord},'%') ")
    List<Product> selectByWd(String keyWord);


    /**
     * 基于商品分类id查询商品信息
     * @param cid
     * @return
     */
    @Select("select id,title,url,price,old_price,view_count,sale_count " +
            " from product " +
            " where category_id=#{cid}")
    List<Product> selectByCid(Integer cid);


    @Insert("insert into product " +
            "(id,title,url,price,old_price,view_count,sale_count,created,category_id) " +
            "values " +
            "(null,#{title},#{url},#{price},#{oldPrice},0,#{saleCount},#{created},#{categoryId})")
    void insert(Product product);


    //======================================

    /**
     * 查询后端所有商品信息
     * @return
     */
    @Select("select id,title,url,price,old_price,view_count,sale_count,created from product")
    public List<Product> selectAdmin();

    /**
     * 基于id执行删除逻辑
     * @param id
     * @return
     */
    @Delete("delete from product where id=#{id}")
    int deleteById(int id);


    @Select("select url from product where id=#{id}")
    String selectUrlById(Integer id);

}
