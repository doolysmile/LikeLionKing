package com.pbs.likelion.LikeLionKing.controller;


import com.pbs.likelion.LikeLionKing.domain.dto.MemberCreateDto;
import com.pbs.likelion.LikeLionKing.domain.dto.MemberDto;
import com.pbs.likelion.LikeLionKing.domain.dto.MemberReadDto;
import com.pbs.likelion.LikeLionKing.domain.dto.MemberUpdateDto;

import com.pbs.likelion.LikeLionKing.domain.Member;
import com.pbs.likelion.LikeLionKing.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usr/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * CRUD TDD로 구현해서 생략.
     */

    @GetMapping("/info")
    public ResponseEntity<MemberReadDto> getMember(@RequestParam("id") Long id) {
        Member member = memberService.findOne(id).orElse(null); // 없으면 null로 반환
        // null에 대한 처리를 어떻게 해야할까
        return ResponseEntity.status(HttpStatus.OK).body(MemberReadDto.from(member));
    }


    @PutMapping("/update")
    public void updateMember(@RequestBody MemberUpdateDto memberUpdateDto) {
        Member member = MemberUpdateDto.toEntity(memberUpdateDto);
        memberService.update(member);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMember(@PathVariable("id") Long id){
        // 궁금한점 : 현재 해당하는 id를 가진 member 있는지를 체크하는게 맞는지
        Member checkMember = memberService.findOne(id).orElse(null);
        memberService.delete(checkMember.getId());
    }

    @PostMapping("/signUp")
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberCreateDto memberCreateDto) {

        MemberDto memberDto = MemberDto.builder()
                .loginId(memberCreateDto.getLoginId())
                .loginPwd(memberCreateDto.getLoginPwd())
                .name(memberCreateDto.getName())
                .role(memberCreateDto.getRole())
                .build();

        Long id = memberService.join(memberDto);
        Member checkMember = memberService.findOne(id).orElse(null);// 저장 됐는지 체크
        return ResponseEntity.status(HttpStatus.OK).body(MemberDto.from(checkMember));
    }




//    @GetMapping("/findAll")
//    public ResponseEntity<List<MemberDto>> findAll(){
//        System.out.println("test");
//        List<MemberDto> memberDtoList = memberService.findMembers();
//        return ResponseEntity.status(HttpStatus.OK).body(memberDtoList);
//    }


}
