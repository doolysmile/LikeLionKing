package lionStudy.lionStudy.repository;

import lionStudy.lionStudy.domain.DTO.PostDto;
import lionStudy.lionStudy.domain.Post;
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

public class JdbcTemplatePostRepository implements PostRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplatePostRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Post save(PostDto post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("userId", post.getUserId());
        parameters.put("title", post.getTitle());
        parameters.put("content", post.getContent());
        parameters.put("views", post.getViews());
        parameters.put("recommended", post.getRecommended());


        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        post.setId(key.longValue());
        return post.toEntity();
    }

    @Override
    public Optional<Post> findById(Long id) {
        List<Post> query = jdbcTemplate.query("select * from post where id = ?", postRowMapper(), id);
        return query.stream().findAny();
    }


    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("select * from post", postRowMapper());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from post where id = ?", id);
    }

    @Override
    public void updateById(String title, String content, Long id) {
        jdbcTemplate.update("update post set title =?, content =? where id = ?", title, content, id);
    }

    @Override
    public void updateTitleById(String title, Long id) {
        jdbcTemplate.update("update post set title =? where id = ?", title, id);
    }

    @Override
    public void updateContentById(String content, Long id) {
        jdbcTemplate.update("update post set content =? where id = ?", content, id);
    }


    private RowMapper<Post> postRowMapper(){
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setUserId(rs.getLong("userId"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            post.setViews(rs.getLong("views"));
            post.setRecommended(rs.getLong("recommended"));

            return post;

        };
    }
}
