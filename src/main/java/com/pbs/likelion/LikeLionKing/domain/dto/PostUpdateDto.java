package com.pbs.likelion.LikeLionKing.domain.dto;

import com.pbs.likelion.LikeLionKing.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostUpdateDto {
    private long id;
    private String title;
    private String content;

    @Builder
    public PostUpdateDto(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static Post toEntity(PostUpdateDto PostUpdateDto) {
        Post build = Post.builder()
                .title(PostUpdateDto.getTitle())
                .content(PostUpdateDto.getContent())
                .build();
        return build;
    }




}
