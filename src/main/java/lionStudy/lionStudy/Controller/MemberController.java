package lionStudy.lionStudy.Controller;


import lionStudy.lionStudy.domain.DTO.MemberDto;
import lionStudy.lionStudy.domain.DTO.MemberReadDto;
import lionStudy.lionStudy.domain.DTO.PostDto;
import lionStudy.lionStudy.domain.Member;
import lionStudy.lionStudy.domain.Post;
import lionStudy.lionStudy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        System.out.println("test");
        Member member = memberService.findOne(id).orElse(null); // 없으면 null로 반환
        // null에 대한 처리를 어떻게 해야할까
        return ResponseEntity.status(HttpStatus.OK).body(MemberReadDto.from(member));
    }



//    @GetMapping("/findAll")
//    public ResponseEntity<List<MemberDto>> findAll(){
//        System.out.println("test");
//        List<MemberDto> memberDtoList = memberService.findMembers();
//        return ResponseEntity.status(HttpStatus.OK).body(memberDtoList);
//    }


}
