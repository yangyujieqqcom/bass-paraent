package com.funo.service;

import com.funo.enity.Wares;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

public interface WareService {


    public List<Wares> findAll();
    public  String buy(@RequestParam("number") String number);
   // public  List<Order> query(@PathVariable("page") int page ,@PathVariable("size") int size);
   public  void update(int id);
    public Set queryFenYe(int page , Long size);
}
