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

    // 중복되지 않은 유효한 id인지 검사
    public Boolean checkId(String loginId) {
        int n = memberRepository.checkId(loginId);
        if(n == 0) {
            return true;
        }
        return false;
    }
}
