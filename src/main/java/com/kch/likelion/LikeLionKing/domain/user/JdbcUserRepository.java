package com.kch.likelion.LikeLionKing.domain.user;

import com.kch.likelion.LikeLionKing.domain.user.domain.User;
import com.kch.likelion.LikeLionKing.domain.user.domain.UserRole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class JdbcUserRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users(loginId,loginPw,nickName,userRole) VALUES (?,?,?,?)", new String[]{"userSeq"});
            ps.setString(1, user.getLoginId());
            ps.setString(2, user.getLoginPw());
            ps.setString(3, user.getNickName());
            ps.setInt(4,user.getUserRole().value());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long generatedSeq = key != null ? key.longValue() : -1;
        return new User.Builder(user)
                .userSeq(generatedSeq)
                .build();
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "UPDATE users SET loginId=?, loginPw=? WHERE userSeq=?",
                user.getLoginId(),
                user.getLoginPw(),
                user.getUserSeq()
        );
    }

    @Override
    public void delete(long userId) {
        jdbcTemplate.update(
                "DELETE FROM USERS WHERE userSeq=?",
                userId
        );
    }

    @Override
    public List<User> findAll() {
        List<User> users = jdbcTemplate.query(
                "SELECT u.* FROM users u",
                mapper
        );
        return null;
    }

    @Override
    public Optional<User> findByLoginId(User user) {
        List<User> users = jdbcTemplate.query(
                "SELECT u.* FROM users u WHERE loginId=?",
                mapper,
                user.getLoginId()
        );

        return ofNullable(users.isEmpty() ? null : users.get(0));
    }

    static RowMapper<User> mapper = (rs, rowMapper) -> new User.Builder()
            .userSeq(rs.getLong("userSeq"))
            .loginId(rs.getString("loginId"))
            .loginPw(rs.getString("loginPw"))
            .nickName(rs.getString("nickName"))
            .userRole(UserRole.valueOf(rs.getInt("userRole")))
            .build();
}
