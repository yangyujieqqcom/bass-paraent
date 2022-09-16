package com.funo.control;


import com.funo.enity.Wares;
import com.funo.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class FeignControl {
    @Autowired
    private FeignService feignService;
    @GetMapping("/consume/")
    public List<Wares> findAll(){
        return  feignService.findAll();
    }
    @RequestMapping(value = "/consume/buy/{number}", method = RequestMethod.GET)
    public String buy(@PathVariable("number") String number) {
        return feignService.buy(number);
    }

    @GetMapping("/consume/query")
    public  List query(){
        return  feignService.query();
    }
    @GetMapping("/consume/query/que/{page}/{size}")
    public Set queryFenYe(@PathVariable("page") int page , @PathVariable("size") Long size){

        return  feignService.queryFenYe(page,size);
    }



}
