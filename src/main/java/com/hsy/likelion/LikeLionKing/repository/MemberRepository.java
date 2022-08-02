package com.hsy.likelion.LikeLionKing.repository;

import com.hsy.likelion.LikeLionKing.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Long save(Member member);       // 회원 정보 저장
    Optional<Member> findById(Long id);  // 회원 id로 회원 정보 조회
    void update(Member member);     // 회원 정보(password) 수정
    void delete(Long id);         // 회원 id로 화원 삭제
}
