package lionStudy.lionStudy.Controller;


import lionStudy.lionStudy.domain.DTO.MemberDto;
import lionStudy.lionStudy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @GetMapping("/findAll")
//    public ResponseEntity<List<MemberDto>> findAll(){
//        System.out.println("test");
//        List<MemberDto> memberDtoList = memberService.findMembers();
//        return ResponseEntity.status(HttpStatus.OK).body(memberDtoList);
//    }


}
