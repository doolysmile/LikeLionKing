package postsite.postsitespring.domain.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postsite.postsitespring.domain.user.domain.User;
import postsite.postsitespring.domain.user.dto.UserCreate;
import postsite.postsitespring.domain.user.dto.UserRead;
import postsite.postsitespring.domain.user.dto.UserUpdate;

import java.util.List;

@RestController
@RequestMapping("/usr/member")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){ this.userService = userService; }

    // Create
    @PostMapping()
    public ResponseEntity<UserCreate.ResponseDto> createMember(
            @RequestBody UserCreate.RequestDto body
    ){
        User user = body.toEntity();
        long id = this.userService.createMember(user);
        user.setId(id);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserCreate.ResponseDto(user));
    }

    // Read
    @GetMapping()
    public ResponseEntity<List<UserRead.ResponseDto>> allMembers(){
        List<User> users = this.userService.allMembers();

        // entity => dto
        List<UserRead.ResponseDto> responseBody = users.stream()
                .map(UserRead.ResponseDto::new).
                toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @GetMapping({"/{memberId}"})
    public ResponseEntity<UserRead.ResponseDto> oneMember(
            @PathVariable Long memberId
    ){
        User user =  this.userService.oneMember(memberId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new UserRead.ResponseDto(user));
    }

    // Update
    @PutMapping({"/{memberId}"})
    public ResponseEntity<String> updateMember(
            @PathVariable Long memberId,
            @RequestBody UserUpdate.RequestDto body
    ){
        User user = userService.oneMember(memberId);

        if(user == null){
            // 에러

        }

        body.updateEntity(user);

       userService.updateMember(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }

    // Delete
    @DeleteMapping({"/{memberId}"})
    public ResponseEntity<String> deleteMember(
            @PathVariable Long memberId
    ){
        this.userService.deleteMember(memberId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }
}
