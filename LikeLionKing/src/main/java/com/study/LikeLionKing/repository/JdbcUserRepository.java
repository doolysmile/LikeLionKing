package com.study.LikeLionKing.repository;

import com.study.LikeLionKing.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JdbcUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("loginId",user.getLoginId());
        parameters.put("loginPw",user.getLoginPw());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        user.setId(key.longValue());
        return user.getId();
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> result = jdbcTemplate.query("select * from user where id = ?",userRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        List<User> result = jdbcTemplate.query("select * from user",userRowMapper());
        return result;
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("update user set loginPw = ? where id = ?",user.getLoginPw(),user.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from user where id = ?", id);
    }
    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setLoginId(rs.getString("loginId"));
            user.setLoginPw(rs.getString("loginPw"));
            return user;
        };
    }
}

