package com.hsy.likelion.LikeLionKing.controller;

import com.hsy.likelion.LikeLionKing.domain.Member;
import com.hsy.likelion.LikeLionKing.dto.MemberCreateDto;
import com.hsy.likelion.LikeLionKing.dto.MemberDto;
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
    public ResponseEntity<MemberCreateDto> createMember(@RequestBody MemberCreateDto memberCreateDto) {
        // MemberCreateDto 적용
        Member member = MemberCreateDto.toEntity(memberCreateDto);
        Member memberCreated = memberService.signUp(member);
        return ResponseEntity.status(HttpStatus.OK).body(MemberCreateDto.toDto(memberCreated));
    }

    @GetMapping("/info")
    public ResponseEntity<Optional<Member>> getMember(@RequestParam("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMember(id));
    }

    @PutMapping("/info")
    public void modifyMember(@RequestBody MemberDto memberDto) {
        Member member = memberDto.toEntity(memberDto);
        memberService.update(member);
        //return ResponseEntity.status(HttpStatus.OK).body(memberRepository.update(member));
    }

    @DeleteMapping("/info/{id}")
    public Long deleteMember(@PathVariable("id") Long id) {
        memberService.delete(id);
        return id;
    }
}
