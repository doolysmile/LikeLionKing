package com.pbs.likelion.LikeLionKing.service;

import com.pbs.likelion.LikeLionKing.repository.MemberRepository;
import com.pbs.likelion.LikeLionKing.domain.dto.MemberDto;
import com.pbs.likelion.LikeLionKing.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;




    @Test
    public void 회원가입() throws Exception {
        //Given
        MemberDto member = new MemberDto();
        member.setName("hello10");
        member.setLoginId("likeLion12");
        member.setLoginPwd("pbs123");
        member.setRole(1);
        //When
        Long saveId = memberService.join(member);
        System.out.println("saveId = " + saveId);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
//    @Test
//    public void 중복_회원_예외() throws Exception {
//        //Given
//        Member member1 = new Member();
//        member1.setName("spring");
//        Member member2 = new Member();
//        member2.setName("spring");
//        //When
//        memberService.join(member1);
//        IllegalStateException e = assertThrows(IllegalStateException.class,
//                () -> memberService.join(member2));//예외가 발생해야 한다.
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//    }
}