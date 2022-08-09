package postsite.postsitespring.domain.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postsite.postsitespring.common.exception.ResourceNotFoundException;
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
        // DTO => Entity
        User user = body.toEntity();

        long id = this.userService.createMember(user);

        // Entity => DTO
        UserCreate.ResponseDto responseDto = new UserCreate.ResponseDto(id, user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    // Read
    @GetMapping()
    public ResponseEntity<List<UserRead.ResponseDto>> allMembers(){
        List<User> users = this.userService.allMembers();

        // Entity => DTO
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
    ) throws ResourceNotFoundException {
        User user = userService
                .oneMember(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :" + memberId));

        // Entity => DTO
        UserRead.ResponseDto responseBody = new UserRead.ResponseDto(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    // Update
    @PutMapping({"/{memberId}"})
    public ResponseEntity<String> updateMember(
            @PathVariable Long memberId,
            @RequestBody UserUpdate.RequestDto body
    ) throws ResourceNotFoundException {
        // Is id Not NULL?
        User user = userService
                .oneMember(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :" + memberId));

        // DTO -> Entity
        body.toEntity(user);

        userService.updateMember(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }

    // Delete
    @DeleteMapping({"/{memberId}"})
    public ResponseEntity<String> deleteMember(
            @PathVariable Long memberId
    ) throws ResourceNotFoundException {
        // Is id Not NULL?
        userService
        .oneMember(memberId)
        .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :" + memberId));

        this.userService.deleteMember(memberId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("success");
    }
}
