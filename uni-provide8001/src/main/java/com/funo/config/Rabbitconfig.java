package com.funo.config;

import com.rabbitmq.client.*;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;


@Configuration
public class Rabbitconfig {

    private static final String Queue_2 = "bassQueue2";
    private static final String exchangeName = "bassName";

    @Bean("dd")
    public Queue queue2() {
        return new Queue(Queue_2, true);
    }

    @Bean("cc")
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(exchangeName);
    }
  /*

   @Bean
    public  Object initBinding1(){
      amqpAdmin.declareBinding(new Binding(Queue_1,Binding.DestinationType.QUEUE,exchangeName,null,null));

     return   new Object();
   }
   @Bean
    public  Object initBinding2(){

      amqpAdmin.declareBinding(new Binding(Queue_2,Binding.DestinationType.QUEUE,exchangeName,null,null));
 return  new  Object();
   }


   */
/*
public  void  o() throws  Exception{
    ConnectionFactory connectionFactory=new ConnectionFactory();
    Connection connection=connectionFactory.newConnection();
    Channel channel=connection.createChannel();
    channel.exchangeDeclare("ss", BuiltinExchange);




}

    public  void f(){

        Map<String,String> a=new HashMap<>();
        a.put("name1","23");
        a.put("name2","24");
        for(Map.Entry<String,String>  c:a.entrySet()){
            c.getKey();

        }

    }


 */

    @Bean()
    public Binding bindingFanoutQueue2( Queue queue2,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }


  /*
    public  void setBinding() {
        Binding binding4 = new Binding(Queue_2, Binding.DestinationType.QUEUE, exchangeName, null, null);
          amqpAdmin.declareBinding(binding4);
    }

   */
}
