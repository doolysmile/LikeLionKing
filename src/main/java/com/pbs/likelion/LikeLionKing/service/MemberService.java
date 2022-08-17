package com.pbs.likelion.LikeLionKing.service;

import com.pbs.likelion.LikeLionKing.repository.MemberRepository;
import com.pbs.likelion.LikeLionKing.domain.dto.MemberDto;
import com.pbs.likelion.LikeLionKing.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */

    public void update(Member member){
        memberRepository.update(member);
    }
    public Long join(MemberDto member){
        validateDuplicateMember(member);
        Member memberEntity = member.toEntity();
        return memberRepository.save(memberEntity).getId();
    }

    private void validateDuplicateMember(MemberDto member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
               throw new IllegalStateException("이미 존재하는 회원입니다.");
           });
        memberRepository.findByLoginId(member.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 ID 입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<MemberDto> findMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberDto> memberDtos = new ArrayList<>();
        for (Member member : members) {
            // (Long id, String loginId, String loginPwd, String name, int role) {
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .loginId(member.getLoginId())
                    .loginPwd(member.getLoginPwd())
                    .name(member.getName())
                    .role(member.getRole())
                    .build();
            memberDtos.add(memberDto);
        }
        return memberDtos;
    }

    /**
     *
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    public void delete(Long id) {
        memberRepository.delete(id);

    }

    public boolean checkId(Long id) {
        int size = memberRepository.checkId(id);
        return (size == 0) ? true : false;

    }
}
