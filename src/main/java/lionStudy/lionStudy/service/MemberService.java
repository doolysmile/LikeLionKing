package lionStudy.lionStudy.service;

import lionStudy.lionStudy.domain.DTO.MemberDto;
import lionStudy.lionStudy.domain.Member;
import lionStudy.lionStudy.repository.MemberRepository;

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
    public Long join(MemberDto member){
        validateDuplicateMember(member);
        return memberRepository.save(member.toEntity()).getId();
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
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     *
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
