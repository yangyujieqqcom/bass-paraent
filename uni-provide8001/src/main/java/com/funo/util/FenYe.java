package com.funo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 分页查询，传参为页数与每页显示条数
 */
@Component
public class FenYe {

 @Autowired
 private RedisTemplate redisTemplate;
 @Autowired
 private StringRedisTemplate stringRedisTemplate;
    public  Set FenYeQuery(int page,Long size){
      //从redis中获取条数，使用set集合装订单信息
        Long total=redisTemplate.opsForZSet().size("message_bass");
        System.out.println("总条数  "+total);
        //根据总条数和每页显示条数计算总页数
        Long totalSize=(total%size==0) ? total/size:total/size+1;
        System.out.println("总页数 "+totalSize);

       //使用set集合装下订单信息查询结果
        Set array=new HashSet<>();
        if(page==1){

            //return  stringRedisTemplate.opsForZSet().range("message_bass",0,size-1);
            array.clear();//确保为第一页的内容
            array.add(stringRedisTemplate.opsForZSet().range("message_bass",0,size-1));
            return  array;



        }
        else if (page>1&page<totalSize){

            return  stringRedisTemplate.opsForZSet().range("message_bass",size*(page-1),size*page-1);



        }
        else if (page==totalSize){
            return  stringRedisTemplate.opsForZSet().range("message_bass",size*(page-1),total-1);

        }
        else {
            array.clear();//确保为页码错误的结果，防止其他数据
            array.add("请输入正确的页码");
            return  array;
        }


    }


}
