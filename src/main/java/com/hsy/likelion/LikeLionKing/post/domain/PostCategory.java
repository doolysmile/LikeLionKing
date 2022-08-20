package com.hsy.likelion.LikeLionKing.post.domain;

public enum PostCategory {
    FREE(0), NOTICE(1);

    private final int value;
    PostCategory(int value) {
        this.value = value;
    }

    // Enum -> Int
    public int getValue() {
        return value;
    }

    // Int -> Enum
    public static PostCategory valueOf(int value) {
        switch (value) {
            case 0:
                return FREE;
            case 1:
                return NOTICE;
        }
        return null;
    }
}
