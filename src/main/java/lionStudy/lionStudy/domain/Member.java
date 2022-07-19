package lionStudy.lionStudy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class Member {



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
