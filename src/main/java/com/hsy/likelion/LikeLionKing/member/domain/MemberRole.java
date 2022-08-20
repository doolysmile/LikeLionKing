package com.hsy.likelion.LikeLionKing.member.domain;

public enum MemberRole {
    // 차례대로 0, 1
    ADMIN(0), USER(1);

    private final int value;

    MemberRole(int value) {
        this.value = value;
    }

    // Enum -> Int
    public int getValue() {
        return value;
    }

    // Int -> Enum
    public static MemberRole valueOf(int value) {
        switch (value) {
            case 0:
                return ADMIN;
            case 1:
                return USER;
        }
        return null;
    }
}
