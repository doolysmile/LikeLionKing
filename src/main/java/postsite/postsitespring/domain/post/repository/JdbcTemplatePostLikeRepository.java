package postsite.postsitespring.domain.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import postsite.postsitespring.domain.post.domain.PostLike;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class JdbcTemplatePostLikeRepository implements PostLikeRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(PostLike postLike) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post_like").usingGeneratedKeyColumns("user_id", "post_id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(postLike);

        jdbcInsert.execute(params);

    }

    @Override
    public long countByPost(Long postId) {
        final String sql = "SELECT COUNT(*) FROM post_like WHERE post_id = ?";
        return jdbcTemplate.update(sql, postId);
    }

    @Override

    public void delete(long postId, long userId) {
        final String sql = "DELETE FROM post_like WHERE article_id = ? and user_id = ?";
        jdbcTemplate.update(sql, postId, userId);
    }
    private RowMapper<PostLike> postLikeRowMapper(){
        return (rs, rowNum) -> PostLike.builder()
                    .userId(rs.getLong("user_id"))
                    .postId(rs.getLong("post_id"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                    .build();

    }
}
