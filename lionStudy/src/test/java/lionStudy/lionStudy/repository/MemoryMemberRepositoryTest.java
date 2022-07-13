package lionStudy.lionStudy.repository;

import lionStudy.lionStudy.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {


    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void aterEeach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);

    }


}
