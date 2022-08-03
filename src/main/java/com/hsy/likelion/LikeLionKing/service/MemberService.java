package com.hsy.likelion.LikeLionKing.service;

import com.hsy.likelion.LikeLionKing.domain.Member;
import com.hsy.likelion.LikeLionKing.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long signUp(Member member) {
        return memberRepository.save(member);
    }

    // 회원 정보 조회
    public Optional<Member> getMember(Long id) {
        return memberRepository.findById(id);
    }

    // 회원 정보 수정
    public void update(Member member) {
        memberRepository.update(member);
    }

    // 회원 삭제
    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public int checkId(Long id) {
        // TODO: 아이디 중복검사할 때 findById 사용해도 되는지
        // 중복 아이디: 1, 아니면: 0
        if (memberRepository.findById(id).orElse(null) == null) {
            return 0;
        }
        return 1;
        //        return memberRepository.checkId(id);
    }
}
