package com.funo.enity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class R {

    private Boolean success;

    private  Integer code;

    private  String message;

    private  Map<String,Object>  map=new HashMap<>();
    //只返回状态码，是否成功，消息提示
    public  R ok(String message,Map map){
        R r=new R();
        r.setCode(ResultCode_Msg_Success.SUCCESS.code);
        r.setSuccess(ResultCode_Msg_Success.SUCCESS.success);
        r.setMessage(message);
        r.setMap(map);
        return r;
    }
 public  R ok2(Map map){
        R r=new R();
     r.setCode(ResultCode_Msg_Success.SUCCESS.code);
     r.setSuccess(ResultCode_Msg_Success.SUCCESS.success);
     r.setMessage(ResultCode_Msg_Success.SUCCESS.message);
     r.setMap(map);
     return  r;
 }
//写死的返回

//根据control层返回
    public  R error2(String message){
        R r=new R();
        r.setSuccess(ResultCode_Msg_Success.FAIL.success);
        r.setCode(ResultCode_Msg_Success.FAIL.code);
        r.setMessage(message);
        return  r;
    }


    public  R ok3(Map map){
        R r =new R();
        r.setCode(ResultCode_Msg_Success.SUCCESS.code);
        r.setSuccess(ResultCode_Msg_Success.SUCCESS.success);
        r.setMessage(ResultCode_Msg_Success.SUCCESS.message);
        r.setMap(map);
        return  r;


    }


}
