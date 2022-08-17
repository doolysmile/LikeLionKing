package com.kch.likelion.LikeLionKing.user.domain;

import com.kch.likelion.LikeLionKing.post.domain.Post;
import com.kch.likelion.LikeLionKing.post.domain.PostDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserDto {
    private Long userSeq;
    private String loginId;
    private String nickName;

    public static UserDto toDto(User user){
        UserDto userDto = UserDto.builder()
                .userSeq(user.getUserSeq())
                .loginId(user.getLoginId())
                .nickName(user.getNickName())
                .build();
        return userDto;
    }
}
