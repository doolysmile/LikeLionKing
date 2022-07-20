package lionStudy.lionStudy.repository;

import lionStudy.lionStudy.domain.DTO.MemberDto;
import lionStudy.lionStudy.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(MemberDto member);

    /**
     * Optional -> find 했는데 null일 경우를 처리해줌
     */
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();



}
