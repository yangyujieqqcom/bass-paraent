package com.funo.service;

import com.funo.enity.Wares;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;

@Service
@FeignClient(value = "CLOUD-PROVIDE9001-SERVE")
public interface FeignService {
    @GetMapping("/")
    public List<Wares> findAll();
    @RequestMapping(value = "/buy/{number}", method = RequestMethod.GET)
    public String buy(@PathVariable("number") String number);

    @GetMapping("/query")
    public  List query();
    @GetMapping("/query/que/{page}/{size}")
    public Set queryFenYe(@PathVariable("page") int page , @PathVariable("size") Long size);



}
