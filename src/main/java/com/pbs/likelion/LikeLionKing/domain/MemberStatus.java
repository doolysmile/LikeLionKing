package com.pbs.likelion.LikeLionKing.domain;

public enum MemberStatus {
    ADMIN(1), USER(2);

    private final int value;

    MemberStatus(int value){
        this.value = value;
    }

    public int value(){
        return value;
    }

    public static MemberStatus valueOf(int value){
        switch (value){
            case 1 :
                return ADMIN;
            case 2 :
                return USER;
            default :
                return null;
        }
    }
}
