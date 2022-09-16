package com.funo.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * redis工具类
 */
@Component
public class RedisUtil {


   @Resource
    private RedisTemplate<String,Object>  redisTemplate;
    //有参构造
   /* public  void  setRedisTemplate(RedisTemplate<String,Object>  redisTemplate){

         this.redisTemplate=redisTemplate;
    }

    */
    //redis中是否有key
    public  boolean hasKey(String key)
    {
        try {
            return  redisTemplate.hasKey(key);
        }catch (Exception e)
        {
            e.printStackTrace();
            return  false;
        }


    }
   //根据key，获取value
    public Object getKey(String key)
    {
        return  key==null?null:redisTemplate.opsForValue().get(key);

    }
    //写入k-v到redis数据库
  public  boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return  true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return  false;
        }

  }




}
