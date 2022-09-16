package com.spring.service.impl;

import com.spring.dao.ArticleDao;
import com.spring.dao.ArticleTagDao;
import com.spring.pojo.Article;
import com.spring.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = false,//是否是只读事务
               timeout = 60,//事务超时时间(秒)
               rollbackFor = Exception.class,//出现什么异常进行回滚
               isolation = Isolation.READ_COMMITTED,//事务隔离级别
               propagation = Propagation.REQUIRED)//事务传播特性propagation
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {
    //private static final Logger log=
      //      LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleTagDao articleTagDao;

    /**
     * 此注解描述的方法为一个事务切入点方法，底层会基于AOP方式进行事务控制，
     * 底层会将事务控制逻辑放到一些通知方法中，然后在通知方法内部进行事务逻辑编写，
     * 例如开启事务、提交事务、回滚事务等。
     * 说明：使用注解描述业务层方法，然后启动事务，进行事务控制的方式，我们称之为
     * 声明式事务控制。
     * @param article
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void insert(Article article) {
        articleDao.insert(article);
        articleTagDao.insert(article.getId(),article.getTagIds());
        log.info("article send ok ");
    }

    @Transactional(readOnly = true)
    @Override
    public Article selectById(Long id) {
        return articleDao.selectById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Article> list() {
        List<Article> list = articleDao.list();
        return list;
    }
}
