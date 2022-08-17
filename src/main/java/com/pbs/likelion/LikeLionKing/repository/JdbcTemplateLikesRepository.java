package com.pbs.likelion.LikeLionKing.repository;

import com.pbs.likelion.LikeLionKing.domain.Likes;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateLikesRepository implements LikesRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateLikesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Likes likes) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("likes").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("memberId", likes.getMemberId());
        parameters.put("postId", likes.getPostId());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        likes.setId(key.longValue());
        return likes.getId();
    }

    @Override
    public Optional<Likes> findById(Long id) {
        List<Likes> query = jdbcTemplate.query("SELECT * FROM likes WHERE id = ? ",
                likeRowMapper(), id);
        return query.stream().findAny();
    }

    @Override
    public Optional<Likes> findByMemberIdAndPostId(Likes likes) {
        List<Likes> query = jdbcTemplate.query("SELECT * FROM likes WHERE memberId = ? AND postId =?"
                , likeRowMapper(), likes.getMemberId(), likes.getPostId());

        return query.stream().findAny();
    }

    @Override
    public void delete(Long id) {
         jdbcTemplate.update("DELETE likes WHERE id = ?", id);
    }


    private RowMapper<Likes> likeRowMapper() {
        return (rs, rowNum) -> {
            Likes likes = new Likes();
            likes.setPostId(rs.getLong("postId"));
            likes.setMemberId(rs.getLong("memberId"));
            likes.setId(rs.getLong("id"));
            return likes;
        };
    }


}
