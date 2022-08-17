package com.pbs.likelion.LikeLionKing.repository;

import com.pbs.likelion.LikeLionKing.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("loginId", member.getLoginId());
        parameters.put("loginPwd", member.getLoginPwd());
        parameters.put("name", member.getName());
        parameters.put("role", member.getRole());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> query = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return query.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();

    }



    @Override
    public Optional<Member> findByLoginId(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    @Override
    public void update(Member member) {
        jdbcTemplate.update("update member set loginPwd = ? where id = ?", member.getLoginPwd(), member.getLoginId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete member where id = ?", id);
    }

    @Override
    public int checkId(Long id) {
        List<Member> query = jdbcTemplate.query("SELECT * FROM member WHERE login_id =?", memberRowMapper(), id);
        return query.size();
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setLoginId(rs.getString("loginId"));
            member.setLoginPwd(rs.getString("loginPwd"));
            member.setName(rs.getString("name"));
            member.setRole(rs.getInt("role"));

            return member;

        };
    }


}
