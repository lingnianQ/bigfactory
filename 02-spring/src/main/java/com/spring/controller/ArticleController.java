package com.spring.controller;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.spring.annotation.RequiredLog;
import com.spring.annotation.RequiredTime;
import com.spring.pojo.Article;
import com.spring.pojo.User;
import com.spring.pojo.vo.JsonResult;
import com.spring.service.ArticleService;
import com.spring.service.impl.ArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags = "文章管理")
@RestController
public class ArticleController {

    //@Resource(name = "articleServiceImpl")
    //@Autowired
    //@Qualifier("articleServiceImpl")
    private ArticleService articleService;

    //构造注入
//    @Autowired
//    public ArticleController(@Qualifier("articleServiceImpl") ArticleService articleService){
//        this.articleService=articleService;
//    }
    @Autowired
    public void setArticleService(@Qualifier("articleServiceImpl") ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * @RequiredLog 注解描述的方法为一个日志切入点方法
     * @param articleId
     * @return
     */
    @ApiImplicitParam(name="articleId",value = "文章id",
            dataType = "long",required = true,example = "1")
    @ApiOperationSupport(order = 20)
    @ApiOperation("查看文章")
    @RequiredTime
    @RequiredLog(operation="浏览具体文章")
    @GetMapping("/article/{articleId}")
    public JsonResult doSelectById(
            @PathVariable("articleId") Long articleId){
        if(articleId<0)
            throw new IllegalArgumentException("文章id不合法");
        Article article=articleService.selectById(articleId);
        return new JsonResult(article);
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation("文章列表")
    @GetMapping("/article/list1")
    public JsonResult doList(){
        List<Article> list = articleService.list();
        return new JsonResult(list);
    }
    @ApiOperationSupport(order = 11)
    @ApiOperation("文章列表")
    @GetMapping("/article/list2/{pageCurrent}/{pageSize}")
    public JsonResult doList(@PathVariable("pageCurrent") Integer pageCurrent,
                             @PathVariable("pageSize") Integer pageSize){
         PageInfo<Object> pageInfo =
                 PageHelper.startPage(pageCurrent, pageSize)
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                         articleService.list();
                    }
                });
        return new JsonResult(pageInfo);
    }

    @ApiOperationSupport(order = 30)
    @ApiOperation("发布文章")
    @PostMapping("/article/create")
    public JsonResult doCreate(@RequestBody Article article, HttpSession session){
        System.out.println("article="+article);
        User user=(User)session.getAttribute("loginUser");
        article.setUserId(user.getId());
        articleService.insert(article);
        return new JsonResult("create ok");
    }

}

