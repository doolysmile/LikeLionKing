package com.kch.likelion.LikeLionKing.post.domain;

public enum BoardType {
    NOTICE_BOARD(1),
    FREE_BOARD(2);

    private final int value;
    BoardType(int value){
        this.value = value;
    }
    public int value(){
        return value;
    }

    public static BoardType valueOf(int value){
        switch (value){
            case 1 :
                return NOTICE_BOARD;
            case 2 :
                return FREE_BOARD;
            default :
                return null;
        }
    }

}
