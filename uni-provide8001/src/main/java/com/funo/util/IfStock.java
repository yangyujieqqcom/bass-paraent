package com.funo.util;

import com.funo.RabbitmqPS.Produce;
import com.funo.dao.WareMapper;
import com.funo.service.WareServiceImp;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * redis实现分布式锁，防止超卖现象
 *
 */
@Component
public class IfStock {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private  StringRedisTemplate stringRedisTemplate;
    @Autowired
    private  LimitRequest limitRequest;
    @Autowired
    private  RedisUtil redisUtil;
    @Autowired
   private WareServiceImp wareServiceImp;
    @Autowired
    private Produce produce;





    public String stockAndResult(String number) {

//判断10s内购买次数
            if (limitRequest.preverntRepeatCommit(number))
            {

              //手机号合法校验
                String res = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
                Pattern pattern = Pattern.compile(res);
                Matcher matcher = pattern.matcher(number);
                Boolean bassResult = matcher.matches();
                if (bassResult) {
                    //设置一个key，作为公共锁的key
                    String lockKey = "product_1";
                    //生成当前客户的UUID
                    String consumeId = UUID.randomUUID().toString();
                    // 用户信息id和产品信息  以便入订单
                    String userId=number.toString();
                    String productId=userId+"product_1";
                    //设置分布式锁，通过setIfAbsent
                    Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, consumeId, 10, TimeUnit.SECONDS);

                    if (!result) {
                        return "稍等一会儿";
                    }
                    try {
                        //获取redis库存
                        int allWares = Integer.parseInt(stringRedisTemplate.opsForValue().get("allWares"));
                        if (allWares > 0) {
                            //如果库存充足，-1
                            int realAllWares = allWares - 1;
                            //更新redis中的库存
                            stringRedisTemplate.opsForValue().set("allWares", realAllWares + "");
                            System.out.println("sucess");
                            //获取redis中的剩余商品库存信息，并更新数据库的库存
                            int allWaresNess=Integer.parseInt(stringRedisTemplate.opsForValue().get("allWares"));
                            wareServiceImp.update(allWaresNess);
                            System.out.println("剩余库存"+"      "+allWaresNess);
                            //购买时间 入订单
                            Calendar calendar=Calendar.getInstance();
                            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
                            String nowTime=dateFormat.format(calendar.getTime());
                            System.out.println(nowTime);
                           // val format = new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()));/
                            ;
                            // String OrderKey=number+"user";
                            //将订单信息 放在集合里  有用户id 当前时间 和产品编号
                           List<String> OrderList=new ArrayList<>();
                            OrderList.add(userId);
                            OrderList.add(nowTime);
                            OrderList.add(productId);
                            System.out.println(OrderList);
                            String OrderKey=OrderList.toString();
                            stringRedisTemplate.opsForValue().set(number,OrderKey);
                            //通过rabbitmq消息队列 将订单消息给生产者
                            produce.sendMessage(OrderKey);



                        // redisUtil.set(OrderKey,OrderList);




                        } else {
                            //库存不够则购买失败
                            System.out.println("fail");
                        }

                    } finally {
                        //首先释放锁，其次防止误删锁以达到加锁的目的
                        if (consumeId.equals(stringRedisTemplate.opsForValue().get(lockKey)))
                            stringRedisTemplate.delete(lockKey);
                    }
                    return "ok";

                }
                else{
                    return  "请输入正确的手机号";
                }
            }



        else{
            return  "休息一会再来";
        }










    }












}