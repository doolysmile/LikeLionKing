package com.study.LikeLionKing.repository;

import com.study.LikeLionKing.domain.Post;
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
public class JdbcPostRepository implements PostRepository
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPostRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", post.getTitle());
        parameters.put("content",post.getContent());
        parameters.put("memberId",post.getMemberId());
        parameters.put("written",post.getWritten());
        parameters.put("lastModified",post.getLastModified());
        parameters.put("views",post.getViews());
        parameters.put("recommended",post.getRecommended());
        parameters.put("postRole",post.getPostRole());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        post.setId(key.longValue());
        return post.getId();
    }

    @Override
    public Optional<Post> findById(Long id) {
        List<Post> result = jdbcTemplate.query("select * from post where id = ?", postRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Post> findAll() {
        List<Post> result = jdbcTemplate.query("select * from post", postRowMapper());
        return result;
    }

    @Override
    public void update(Post post) {
        jdbcTemplate.update("update post set title = ?, content = ? where id = ?",post.getTitle(),post.getContent(),post.getId() );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from post where id = ?", id);
    }

    @Override
    public void viewsInc(Long id) {
        System.out.println("실행됨");
        jdbcTemplate.update("update post set views=views+1 where id = ?",id );
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            post.setMemberId(rs.getLong("memberId"));
            post.setWritten(rs.getString("written"));
            post.setLastModified(rs.getString("lastModified"));
            post.setViews(rs.getLong("views"));
            post.setRecommended(rs.getLong("recommended"));
            post.setPostRole(rs.getInt("postRole"));
            return post;
        };
    }
}
