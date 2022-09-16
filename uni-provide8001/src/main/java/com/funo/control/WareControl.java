package com.funo.control;
import com.funo.RabbitmqPS.Produce;
import com.funo.enity.R;
import com.funo.service.WareServiceImp;
import com.funo.util.IfStock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.funo.enity.Wares;

import java.util.*;

/**
 *
 *
 */
@RestController
@Api(value = "接口层")
public class WareControl {
    @Resource
    private WareServiceImp wareServiceImp;
    @Resource
    private  R r;
    @Resource
    private IfStock ifStock;
    @Resource
private StringRedisTemplate stringRedisTemplate;


    /**
     *
     * @return
     */
    @ApiOperation(value = "查看商品信息接口")
    @ResponseBody
    @GetMapping("/")
    public R findAll() {

        List<Wares> list=wareServiceImp.findAll();
        Map map=new HashMap<>();
        map.put("data",list);
        return r.ok2(map);
    }
   @ApiOperation(value = "输入手机号买商品接口")
    @RequestMapping(value = "/buy/{number}", method = RequestMethod.GET)
    public R buy(@ApiParam(value = "传入的手机号")@PathVariable("number") String number) {


        if (number.toString().length() == 11) {
            String s=ifStock.stockAndResult(number);
            String s1=stringRedisTemplate.opsForValue().get(number);
            String s2="该账户"+number+"购买商品成功";
            Map map=new HashMap<>();
            map.put(s2,s1);
            return r.ok(s,map);
        } else {
            return r.error2("请输入11位手机号");
        }

    }



@ApiOperation(value = "查询所有订单接口")
@ResponseBody
@GetMapping("/query")
    public  R query(){
  List list=  wareServiceImp.query();
  Map map=new HashMap<>();
  map.put("data",list);
  return  r.ok2(map);

}


@ApiOperation(value = "分页查询接口")
@ResponseBody
@GetMapping("/query/que/{page}/{size}")
    public R queryFenYe(@ApiParam(value = "传入的页数") @PathVariable("page") int page , @ApiParam(value = "每页显示的条数")@PathVariable("size") Long size){


      Set set=wareServiceImp.queryFenYe(page,size);
      Map map=new HashMap<>();
      map.put("data",set);
      return  r.ok3(map);



    }




}