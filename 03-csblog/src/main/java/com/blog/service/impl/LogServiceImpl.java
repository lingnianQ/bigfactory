package com.blog.service.impl;

import com.blog.mapper.LogMapper;
import com.blog.pojo.Log;
import com.blog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Async
    @Override
    public void insert(Log log) {
        logMapper.insert(log);
    }
}
