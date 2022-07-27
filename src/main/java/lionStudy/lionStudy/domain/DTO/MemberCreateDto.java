package lionStudy.lionStudy.domain.DTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberCreateDto {

    private Long id;
    private String loginId;  // 로그인 아이디
    private String loginPwd; // 로그인 비밀번호
    private String name;
    private int role; // ADMIN, USER;
    public MemberCreateDto(Long id, String loginId, String loginPwd, String name, int role) {
        this.id = id;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.name = name;
        this.role = role;
    }
}



