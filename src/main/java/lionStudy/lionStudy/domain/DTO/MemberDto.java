package lionStudy.lionStudy.domain.DTO;


import lionStudy.lionStudy.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
