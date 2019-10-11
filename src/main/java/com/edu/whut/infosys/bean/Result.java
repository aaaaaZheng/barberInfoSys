package com.edu.whut.infosys.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * @author aaaaaaa
 * mag 返回的信息
 * success请求时候成功
 * detail返回的数据
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private String message;
    private boolean success;
    private  T detail;
    public void setSuccessAndMsg(boolean aSuccess,String aMsg){
        message =aMsg;
        success=aSuccess;
    }
    public void setSuccessAndMsgAndDetail(boolean aSuccess,String aMsg,T aDetail){
        message =aMsg;
        success=aSuccess;
        detail=aDetail;
    }

}
