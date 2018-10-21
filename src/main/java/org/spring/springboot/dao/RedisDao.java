package org.spring.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/4/14.
 */
@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate template;

    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value,1000, TimeUnit.DAYS);//1分钟过期
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }

    public  Boolean isHaveKey(String key){
        Boolean isHashKey = template.hasKey(key);
        return isHashKey;
    }

    public  void setKeyByMin(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value,5, TimeUnit.MINUTES);//1分钟过期
    }
}