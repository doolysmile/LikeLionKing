package lionStudy.lionStudy.domain.DTO;

import lionStudy.lionStudy.domain.Member;
import lombok.Getter;

@Getter
public class MemberReadDto {

    private String logindId;
    private String loginPwd;
    private int role; // 운영자 or 유저

    public MemberReadDto(String logindId, String loginPwd, int role) {
        this.logindId = logindId;
        this.loginPwd = loginPwd;
        this.role = role;
    }

    public static MemberReadDto from(Member member){
        return new MemberReadDto(member.getLoginId(), member.getLoginPwd(), member.getRole());
    }
}
