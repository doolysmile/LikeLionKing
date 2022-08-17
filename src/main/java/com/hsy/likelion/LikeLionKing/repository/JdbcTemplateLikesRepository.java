package com.hsy.likelion.LikeLionKing.repository;

import com.hsy.likelion.LikeLionKing.domain.Likes;
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
public class JdbcTemplateLikesRepository implements LikesRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateLikesRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(Likes likes) {
        // 테이블명, pk, 컬럼 정보 -> insert문 자동 생성
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("likes").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("member_id", likes.getMemberId());
        parameters.put("post_id", likes.getPostId());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        likes.setId(key.longValue());

        return likes.getId();
    }

    @Override
    public Optional<Likes> findByMemberIdAndPostId(Likes likes) {
        String sql = "SELECT * FROM likes WHERE member_id = ? AND post_id = ?";
        List<Likes> likesList = jdbcTemplate.query(sql, likesRowMapper(), likes.getMemberId(), likes.getPostId());
        return likesList.stream().findAny();
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE likes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void increaseLikes(Long postId) {
        // 게시글 좋아요 수 1 증가
        String sql = "UPDATE post SET likes = likes + 1 WHERE id = ?";
        jdbcTemplate.update(sql, postId);
    }

    @Override
    public void decreaseLikes(Long postId) {
        // 게시글 좋아요 수 1 감소
        String sql = "UPDATE post SET likes = likes - 1 WHERE id = ?";
        jdbcTemplate.update(sql, postId);
    }

    private RowMapper<Likes> likesRowMapper() {
        return (rs, rowNum) -> {
            // "컬럼명"으로 해당 타입 데이터를 받아와 Member 객체로 반환
            Likes likes = Likes.builder()
                    .id(rs.getLong("id"))
                    .memberId(rs.getLong("member_id"))
                    .postId(rs.getLong("post_id"))
                    .build();
            return likes;
        };
    }
}
