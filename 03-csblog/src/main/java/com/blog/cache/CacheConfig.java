package com.blog.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager=new SimpleCacheManager();
        CacheEnum[] values = CacheEnum.values();
        List<CaffeineCache> caches=new ArrayList<>();
        for(CacheEnum cacheEnum:values){
            CaffeineCache caffeineCache = new CaffeineCache(cacheEnum.getName(),
                    Caffeine.newBuilder()
                            .initialCapacity(100)
                            .maximumSize(1000)
                            .expireAfterWrite(cacheEnum.getExpires(), TimeUnit.MINUTES).build());
            caches.add(caffeineCache);
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
