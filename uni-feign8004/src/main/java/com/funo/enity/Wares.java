package com.funo.enity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wares {
    private  int id;
    private  int price;
    private  int number;
    private  String name;

}
