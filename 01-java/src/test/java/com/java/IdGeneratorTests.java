package com.java;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorTests {

    @Test
    public void testGeneratorId(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        System.out.println(format);
        AtomicLong atomicLong=new AtomicLong(1000);
        String id=format+atomicLong.incrementAndGet();
        System.out.println(id);
        id=format+atomicLong.incrementAndGet();
        System.out.println(id);

    }
}
