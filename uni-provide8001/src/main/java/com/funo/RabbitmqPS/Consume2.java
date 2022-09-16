package com.funo.RabbitmqPS;

import com.funo.util.RedisUtil;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

@Component
public class Consume2   {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = "bassQueue2")
    public void  messageReceive2(Message message, Channel channel)throws IOException {


        String CountKey="IdCard";
        double count=stringRedisTemplate.opsForValue().increment(CountKey,1);
        String  ConsumeKey="message"+(int)count;
        String BassConsumeKey="message_bass";
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        System.out.println("我是消费2"+"    "+new String(message.getBody())+"          FUNOFUNOFUNO"+"         "+(int)count);
        stringRedisTemplate.opsForValue().set(ConsumeKey,new String(message.getBody()));
        ZSetOperations<String,String> listData=stringRedisTemplate.opsForZSet();
        listData.add(BassConsumeKey,new String(message.getBody()),count);
     Long size= redisTemplate.opsForZSet().size(BassConsumeKey);
        System.out.println("条数     " +size);
    }
}
















