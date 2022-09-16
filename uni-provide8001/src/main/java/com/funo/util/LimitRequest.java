package com.funo.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 限制用户一直购买，10s内只能购买一次
 */
@Component
public class LimitRequest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
   public Boolean preverntRepeatCommit(String number)
    {
        //根据电话号码生成一个用户对应唯一key
        String countKey="count"+number;
        //每购买一次 count就会加1
        double count=stringRedisTemplate.opsForValue().increment(countKey,1);
        //通过count判断  如果为1则为10s内第一次购买，如果为大于1则10s第二次购买拒绝购买
        if(count==1)
        {
            //这个key的过期时间为10s，10s以后重置count次数
            stringRedisTemplate.expire(countKey,10, TimeUnit.SECONDS);
            return true;
        }
        else {
            return  false;
        }
    }

}
