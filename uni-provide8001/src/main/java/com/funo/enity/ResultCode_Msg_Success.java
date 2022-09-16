package com.funo.enity;

import io.swagger.annotations.ApiModel;



/**
 *
 * 状态码 信息 是否成功可能情况
 */
@ApiModel(value = "可能会出现的返回情况")
public enum ResultCode_Msg_Success {

    SUCCESS(true,20000,"成功"),
    FAIL(false,30000,"失败");
    public Boolean success;
    public Integer code;
    public  String message;
      ResultCode_Msg_Success(Boolean success,Integer code,String message){
          this.success=success;
          this.code=code;
          this.message=message;
    }






}
