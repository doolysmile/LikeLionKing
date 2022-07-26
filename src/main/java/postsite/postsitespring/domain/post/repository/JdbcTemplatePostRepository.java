package postsite.postsitespring.domain.post.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import postsite.postsitespring.domain.post.domain.Post;
import postsite.postsitespring.domain.post.dto.PostCreate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplatePostRepository implements PostRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePostRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(Post post) {
        // jdbc 설정
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("id");
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("title", post.getTitle());
//        parameters.put("content", post.getContent());

        // getter를 통해 자동으로 객체의 필드값 추출.
        SqlParameterSource params = new BeanPropertySqlParameterSource(post);

        Long id = jdbcInsert.executeAndReturnKey(params).longValue();

        return id;
    }

    @Override
    public Post findById(Long id) {
        final String sql = "select * from post where id = ?";
        Post result = jdbcTemplate.queryForObject(sql, postRowMapper(), id);
        return result;
    }


    @Override
    public List<Post> findAll(Long boardId, Long page) {
        final String sql = "SELECT * FROM post ORDER BY id DESC LIMIT ? OFFSET ?" ;
        return jdbcTemplate.query(sql, postRowMapper(),10,page);
    }

    @Override
    public List<Post> findAll(Long boardId, Long page, String searchKeyword) {
        final String sql = "SELECT * FROM post WHERE title LIKE ? ORDER BY id DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, postRowMapper(),'%' + searchKeyword + '%', 10, page);
    }

    @Override
    public void update(Post post) {
        final String sql = "UPDATE post SET title=?, content=? WHERE id=?";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getId());
    }

    @Override
    public void delete(Long id) {
        final String sql = "DELETE FROM post WHERE id =?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Post> postRowMapper(){
        return (rs, rowNum) -> {
            Post post = Post.builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .isNotice(rs.getInt("is_notice") > 0)
                    .views(rs.getInt("views"))
                    .likes(rs.getInt("likes"))
                    .createdAt(rs.getTimestamp("created_at"))
                    .updatedAt(rs.getTimestamp("updated_at"))
                    .build();
            return post;
        };
    }
}
