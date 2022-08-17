package com.pbs.likelion.LikeLionKing.repository;

import com.pbs.likelion.LikeLionKing.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    /**
     * Optional -> find 했는데 null일 경우를 처리해줌
     */
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll();
    void update(Member member);

    void delete(Long id);


    int checkId(Long id);
}
