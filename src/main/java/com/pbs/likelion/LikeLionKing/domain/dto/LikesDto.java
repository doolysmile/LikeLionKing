package com.pbs.likelion.LikeLionKing.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LikesDto {

    private long id;
    private long memberId;
    private long postId;

}
