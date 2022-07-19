package lionStudy.lionStudy.domain.DTO;


import lionStudy.lionStudy.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter

public class MemberDto {


    private Long id;
    private String name;
    private int role; // ADMIN, USER;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.role = member.getRole();

    }
}
