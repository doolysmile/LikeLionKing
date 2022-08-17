package postsite.postsitespring.domain.post.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import postsite.postsitespring.domain.post.domain.Post;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplatePostRepository implements PostRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePostRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Post post) {
        // jdbc 설정
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("id");

        // getter를 통해 자동으로 객체의 필드값 추출.
        SqlParameterSource params = new BeanPropertySqlParameterSource(post);

        Long id = jdbcInsert.executeAndReturnKey(params).longValue();

        return id;
    }

    @Override
    public Optional<Post> findById(Long id) {
        final String sql = "select * from post where id = ?";
        List<Post> result =  jdbcTemplate.query(sql, postRowMapper(), id);
        return result.stream().findAny();
    }


    @Override
    public List<Post> findAll(Long boardId, Long page) {
        final String sql = "SELECT * FROM post WHERE post_group_id = ? ORDER BY id DESC LIMIT ? OFFSET ?" ;
        return jdbcTemplate.query(sql, postRowMapper(), boardId, 10, page);
    }

    @Override
    public List<Post> findAll(Long boardId, Long page, String searchKeyword) {
        final String sql = "SELECT * FROM post WHERE title LIKE ?  AND post_group_id = ? ORDER BY id DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, postRowMapper(),'%' + searchKeyword + '%', boardId, 10, page);
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
                    .isNotice(rs.getByte("is_notice"))
                    .postGroupId(rs.getInt("post_group_id"))
                    .views(rs.getInt("views"))
                    .createdAt(rs.getTimestamp("created_at"))
                    .updatedAt(rs.getTimestamp("updated_at"))
                    .build();
            return post;
        };
    }
}
