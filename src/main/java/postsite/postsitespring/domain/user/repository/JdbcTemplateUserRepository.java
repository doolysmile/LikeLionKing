package postsite.postsitespring.domain.user.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import postsite.postsitespring.domain.user.domain.User;
import postsite.postsitespring.domain.user.domain.UserRoleTypeEnum;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcTemplateUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) { jdbcTemplate = new JdbcTemplate(dataSource); }

    @Override
    public long save(User user) {
        // jdbc setting
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");

        // getter를 통해 자동으로 객체의 필드값 추출
        SqlParameterSource params = new BeanPropertySqlParameterSource(user){
            // enum bug fix
            @Override
            public Object getValue(String paramName) throws IllegalArgumentException {
                Object value = super.getValue(paramName);
                if (value instanceof Enum) {
                    return value.toString();
                }
                return value;
            }
        };

        long id = jdbcInsert.executeAndReturnKey(params).longValue();

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
        final String sql = "UPDATE user SET login_id=?, login_pw=?, nickname=? WHERE id=?";
        jdbcTemplate.update(sql, user.getLoginId(), user.getLoginPw(), user.getNickname(), user.getId());
    }

    @Override
    public void delete(Long id) {
        final String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<User> userRowMapper(){
        return (rs, rowNum) -> {
            User user = User.builder()
                    .id(rs.getLong("id"))
                    .loginId(rs.getString("login_id"))
                    .loginPw(rs.getString("login_pw"))
                    .nickname(rs.getString("nickname"))
                    .role(UserRoleTypeEnum.valueOf(rs.getString("role")))
                    .level(rs.getShort("level"))
                    .createdAt(rs.getTimestamp("created_at"))
                    .updatedAt(rs.getTimestamp("updated_at"))
                    .build();
            return user;
        };
    }
}
