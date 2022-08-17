package com.kch.likelion.LikeLionKing.global.error;

public class DuplicateException extends RuntimeException{

    public DuplicateException(){
        super();
    }
    public DuplicateException(String message){
        super(message);
    }

}
