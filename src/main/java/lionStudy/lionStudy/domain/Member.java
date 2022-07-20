package lionStudy.lionStudy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Member {
    /**
     * 고민 -> loginId 자체를 primaryKey 지정을 할지,,
     */
    private Long id;
    private String loginId;  // 로그인 아이디
    private String loginPwd; // 로그인 비밀번호
    private String name;
    private int role; // ADMIN, USER;

    public Member(){}

    public Member(Long id, String loginId, String loginPwd, String name, int role) {
        this.id = id;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.name = name;
        this.role = role;
    }

    static public class Builder {

        private Long id;
        private String loginId;  // 로그인 아이디
        private String loginPwd; // 로그인 비밀번호
        private String name;
        private int role; // ADMIN, USER;

        public Builder(){}
        public Builder(Long id, String loginId, String loginPwd, String name, int role) {
            this.id = id;
            this.loginId = loginId;
            this.loginPwd = loginPwd;
            this.name = name;
            this.role = role;
        }

        public Member build() {
            return new Member(id, loginId, loginPwd, name, role);
        }


    }

}
