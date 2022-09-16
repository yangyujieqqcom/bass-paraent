package com.funo.enity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "商品信息")
public class Wares implements Serializable {
@ApiModelProperty("商品编号")
    private  int id;
@ApiModelProperty("商品价格")
    private  int price;
@ApiModelProperty("商品数量")
    private  int number;
@ApiModelProperty("商品名字")
    private  String name;
}
