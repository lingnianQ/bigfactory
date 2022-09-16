package com.spring.service.impl;

import com.spring.pojo.Log;
import com.spring.service.LogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    //@Autowired
    //private LogDao logDao;

    /**
     * @Async描述的方法为一个异步切入点方法，
     * 此方法会在spring提供的线程池中的线程上去运行。
     * @param log
     */
    @Async
    @Override
    public void insert(Log log) {
        System.out.println(Thread.currentThread().getName()+"-->LogServiceImpl.insert");
        try{Thread.sleep(5000);}catch (Exception e){}//模拟耗时操作
        System.out.println(log);
       //logDao.insert(log);
    }

}
