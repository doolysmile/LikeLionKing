package lionStudy.lionStudy.domain.DTO;


import lionStudy.lionStudy.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Getter
@Setter
public class MemberDto {



    private Long id;
    private String loginId;  // 로그인 아이디
    private String loginPwd; // 로그인 비밀번호
    private String name;
    private int role; // ADMIN, USER;

    public MemberDto(){}

    public MemberDto(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.loginPwd = member.getLoginPwd();
        this.name = member.getName();
        this.role = member.getRole();
    }

    public Member toEntity(){
        Member mebmer = new Member.Builder(id, loginId, loginPwd, name, role).build();

        return mebmer;
    }

}
