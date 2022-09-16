package com.funo.RabbitmqPS;

import com.funo.config.Rabbitconfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Produce {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Rabbitconfig rabbitconfig;

    public  void sendMessage(String OrderKey ){
        String message=OrderKey;
        try{
            /*try{
                Thread.sleep(3000);
            }catch (InterruptedException _ignored){
                Thread.currentThread().interrupt();
            }


             */

            rabbitTemplate.convertAndSend("bassName","",message);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
