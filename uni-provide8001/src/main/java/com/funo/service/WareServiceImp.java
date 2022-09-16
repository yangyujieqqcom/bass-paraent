package com.funo.service;


import com.funo.dao.WareMapper;
import com.funo.enity.Wares;
import com.funo.util.FenYe;
import com.funo.util.IfStock;
import com.funo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class WareServiceImp implements  WareService {
    @Autowired
    private WareMapper wareMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IfStock ifStock;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private FenYe fenYe;

    public List<Wares> findAll() {
        String key = "allAll";
        List<Wares> wares = (List<Wares>) redisUtil.getKey(key);
        //
        if (CollectionUtils.isEmpty(wares)) {
            wares = wareMapper.findAll();
            redisUtil.set(key, wares);
        }
        return wareMapper.findAll();
    }

    public String buy(String number) {
        return ifStock.stockAndResult(number);

    }

    public List query() {
        int allNumber = Integer.parseInt(stringRedisTemplate.opsForValue().get("IdCard"));
        List list = new ArrayList<>();
        for (int i = 1; i <= allNumber; i++) {
            String QueryKey = "message" + i;
            if (redisUtil.hasKey(QueryKey)) {
                list.add(stringRedisTemplate.opsForValue().get(QueryKey));
            }

        }
        return list;

    }
    public  void update(int id){
        wareMapper.update(id);

    }


    public Set queryFenYe(int page, Long size){

      return   fenYe.FenYeQuery(page,size);
    }


}