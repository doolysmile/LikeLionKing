package postsite.postsitespring.domain.user.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import postsite.postsitespring.domain.user.domain.User;
import postsite.postsitespring.domain.user.dto.UserCreate;
import postsite.postsitespring.domain.user.dto.UserUpdate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) { jdbcTemplate = new JdbcTemplate(dataSource); }

    @Override
    public long save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>();
        params.put("loginId", user.getLoginId());
        params.put("loginPw", user.getLoginPw());

        long id = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params)).longValue();

        return id;
    }

    @Override
    public User findById(Long id) {
        final String sql = "SELECT * from user where id = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper(),id);
    }

    @Override
    public List<User> findAll() {
        final String sql = "SELECT * from user";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    @Override
    public void update(User user) {
        final String sql = "UPDATE user SET loginId=?, loginPw=? WHERE id=?";
        jdbcTemplate.update(sql, user.getLoginId(), user.getLoginPw(), user.getId());
    }

    @Override
    public void delete(Long id) {
        final String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<User> userRowMapper(){
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setLoginId(rs.getString("loginId"));
            user.setLoginPw(rs.getString("loginPw"));
            return user;
        };
    }
}
