package com.kch.likelion.LikeLionKing.domain.like.repository;

import com.kch.likelion.LikeLionKing.domain.like.domain.Like;
import com.kch.likelion.LikeLionKing.domain.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JdbcLikeRepository implements LikeRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Like> findByUserIdAndPostId(Long userId, Long postId) {
        return jdbcTemplate.query(
                "SELECT * FROM likes WHERE userId=? AND postId=?",
                mapper,
                userId, postId
        ).stream().findAny();
    }

    @Override
    public Like save(Like like) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO likes(userId,postId) VALUES (?,?)", new String[]{"likeSeq"});
            ps.setLong(1, like.getUserId());
            ps.setLong(2, like.getPostId());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long generatedSeq = key != null ? key.longValue() : -1;
        return  Like.builder()
                .likeSeq(generatedSeq)
                .userId(like.getUserId())
                .postId(like.getPostId())
                .build();
    }

    @Override
    public void delete(Long userId, Long postId) {
        jdbcTemplate.update(
                "DELETE FROM LIKES WHERE userId=? AND postId=?",
                userId, postId
        );
    }


    static RowMapper<Like> mapper = (rs, rowMapper) -> new Like().builder()
                .likeSeq(rs.getLong("likeSeq"))
                .userId(rs.getLong("userId"))
                .postId(rs.getLong("postId"))
                .build();

}
