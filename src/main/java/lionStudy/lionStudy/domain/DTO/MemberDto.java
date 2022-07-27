package lionStudy.lionStudy.domain.DTO;


import lionStudy.lionStudy.domain.Member;
import lionStudy.lionStudy.domain.Post;
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

    @Builder
    public MemberDto(Long id, String loginId, String loginPwd, String name, int role) {
        this.id = id;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.name = name;
        this.role = role;
    }

    public static MemberDto from(Member member) {
        return new MemberDto(member.getId(), member.getLoginId(), member.getLoginPwd(), member.getName(), member.getRole());
    }

    public Member toEntity(){
        Member mebmer = new Member.Builder(id, loginId, loginPwd, name, role).build();
        return mebmer;
    }

}
