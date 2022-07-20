package com.hsy.likelion.LikeLionKing.repository;

import com.hsy.likelion.LikeLionKing.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        // 테이블명, pk, 컬럼 정보 -> insert문 자동 생성
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        // 파라미터
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("login_id", member.getLoginId());
        parameters.put("login_pw", member.getLoginPw());
        // 추가된 컬럼
        parameters.put("nickname", member.getNickname());
        parameters.put("email", member.getEmail());
        parameters.put("phone", member.getPhone());
        parameters.put("role", member.getRole());
        // DB에서
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;  // 회원 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> members = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return members.stream().findAny();
    }

    @Override
    public void update(Member member) {
        jdbcTemplate.update("update member set login_pw = ?, nickname = ?, eamil= ?, phone = ? where id = ?", member.getLoginPw(), member.getNickname(), member.getEmail(), member.getPhone(), member.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete member where id = ?", id);
    }

    // 쿼리 결과를 RowMapper로 매핑하여 원하는 자바 객체로 변환하는 메서드
    // 쿼리 결과값(Row 값)들을 RowMapper를 이용해 ResultSet -> 자바 객체로 변환
    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            // "컬럼명"으로 해당 타입 데이터를 받아와 Member 객체로 반환
            member.setId(rs.getLong("id"));
            member.setLoginId(rs.getString("login_id"));
            member.setLoginPw(rs.getString("login_pw"));
            member.setNickname(rs.getString("nickname"));
            member.setEmail(rs.getString("email"));
            member.setPhone(rs.getString("phone"));
            member.setRole(rs.getInt("role"));
            return member;
        };
    }
}
