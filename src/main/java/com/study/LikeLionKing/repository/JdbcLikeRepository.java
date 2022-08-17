package com.study.LikeLionKing.repository;

import com.study.LikeLionKing.domain.Like;
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
public class JdbcLikeRepository implements LikeRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcLikeRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(Like like) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("like").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId",like.getUserId());
        parameters.put("postId",like.getPostId());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));

        like.setId(key.longValue());
        return like.getId();
    }

    @Override
    public Optional<Like> findById(Long id) {
        List<Like> result = jdbcTemplate.query("select * from like where id = ?", likeRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Like> findByUserIdAndPostId(Long userId,Long postId) {
        List<Like> result = jdbcTemplate.query("select * from like where userId = ? AND post_id = ?", likeRowMapper(), userId,postId);
        return result.stream().findAny();
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from like where id = ?", id);
    }

    private RowMapper<Like> likeRowMapper() {
        return (rs, rowNum) -> {
            Like like = new Like();
            like.setPostId(rs.getLong("postId"));
            like.setId(rs.getLong("id"));
            like.setUserId(rs.getLong("userId"));
            return like;
        };
    }
}
