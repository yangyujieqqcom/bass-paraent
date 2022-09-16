package com.funo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

     @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory){


         //json序列化  RedisTmplate对象
         RedisTemplate<String,Object> template= new RedisTemplate<>();
         template.setConnectionFactory(connectionFactory);//连接工厂
         GenericJackson2JsonRedisSerializer jsonRedisSerializer=new GenericJackson2JsonRedisSerializer();
         //设置k的序列化
         template.setKeySerializer(RedisSerializer.string());
         template.setHashValueSerializer(RedisSerializer.string());
         //设置v的序列化
         template.setValueSerializer(jsonRedisSerializer);
         template.setHashValueSerializer(jsonRedisSerializer);

         return  template;

















     }



}
