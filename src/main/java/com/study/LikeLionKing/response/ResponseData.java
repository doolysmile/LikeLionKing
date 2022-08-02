package com.study.LikeLionKing.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseData <T>{
    private String resultCode;
    private final String msg;
    private T data;

    public static ResponseData successResponse(Object data){
        return new ResponseData("s-1","성공",data);
    }

    public static ResponseData failResponse(Object data){
        return new ResponseData("f-1","실패",data);
    }
}
