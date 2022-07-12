package com.hsy.likelion.LikeLionKing.controller;

import com.hsy.likelion.LikeLionKing.domain.Member;
import com.hsy.likelion.LikeLionKing.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usr/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<Object> createMember(@RequestBody Member member) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.signUp(member));
    }

    @GetMapping("/info")
    public ResponseEntity<Optional<Member>> getMember(@RequestParam("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMember(id));
    }

    @PutMapping("/info")
    public Member modifyMember(@RequestBody Member member) {
        memberService.update(member);
        return member;
        //return ResponseEntity.status(HttpStatus.OK).body(memberRepository.update(member));
    }

    @DeleteMapping("/info/{id}")
    public Long deleteMember(@PathVariable("id") Long id) {
        memberService.delete(id);
        return id;
    }

//    @GetMapping("/signUp")
//    public ResponseEntity<Object> createMember(@RequestParam("loginId") String loginId, @RequestParam("loginPw") String loginPw) {
//        Member member = new Member();
//        member.setLoginId(loginId);
//        member.setLoginPw(loginPw);
//        return ResponseEntity.status(HttpStatus.OK).body(memberService.signUp(member));
//    }
//
//    @GetMapping("/info")
//    public ResponseEntity<Optional<Member>> getMember(@RequestParam("id") Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMember(id));
//    }
//
//    @GetMapping("/info/modify")
//    public Member modifyMember(@RequestParam("id") Long id, @RequestParam("loginId") String loginId, @RequestParam("loginPw") String loginPw) {
//        Member member = new Member();
//        member.setId(id);
//        member.setLoginId(loginId);
//        member.setLoginPw(loginPw);
//        memberService.update(member);
//        return member;
//        //return ResponseEntity.status(HttpStatus.OK).body(memberRepository.update(member));
//    }
//
//    @GetMapping("/info/{id}")
//    public Long deleteMember(@PathVariable("id") Long id) {
//        memberService.delete(id);
//        return id;
//    }
}
