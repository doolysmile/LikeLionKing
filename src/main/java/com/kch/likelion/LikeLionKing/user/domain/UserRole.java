package com.kch.likelion.LikeLionKing.user.domain;

public enum UserRole {
    ADMIN_USER(1),
    NORMAL_USER(2);

    private final int value;
    UserRole(int value){
        this.value = value;
    }
    public int value(){
        return value;
    }

    public static UserRole valueOf(int value){
        switch (value){
            case 1 :
                return ADMIN_USER;
            case 2 :
                return NORMAL_USER;
            default :
                return null;
        }
    }
}
