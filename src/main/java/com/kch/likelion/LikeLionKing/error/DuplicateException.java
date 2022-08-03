package com.kch.likelion.LikeLionKing.error;

public class DuplicateException extends RuntimeException{

    public DuplicateException(){
        super();
    }
    public DuplicateException(String message){
        super(message);
    }

}
