package postsite.postsitespring.domain.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import postsite.postsitespring.domain.user.domain.User;
import postsite.postsitespring.domain.user.dto.UserCreate;
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
    public UserCreate.ResponseDto createMember(@RequestBody UserCreate.RequestDto body){
        User user = body.toEntity();
        long id = this.userService.createMember(user);
        user.setId(id);

        return new UserCreate.ResponseDto(user);
    }

    // Read
    @GetMapping()
    public List<User> allMembers(){
        return this.userService.allMembers();
    }

    @GetMapping({"/{memberId}"})
    public User oneMember(
            @PathVariable Long memberId
    ){
        return this.userService.oneMember(memberId);
    }

    // Update
    @PutMapping({"/{memberId}"})
    public String updateMember(
            @PathVariable Long memberId,
            @RequestBody UserUpdate.RequestDto body
    ){
        User user = body.toEntity();
        user.setId(memberId);
        this.userService.updateMember(user);

        return "success";
    }

    // Delete
    @DeleteMapping({"/{memberId}"})
    public String deleteMember(
            @PathVariable Long memberId
    ){
        this.userService.deleteMember(memberId);
        return "success";
    }


}
