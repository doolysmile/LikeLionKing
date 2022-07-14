package com.kch.likelion.LikeLionKing.post.repository;


import com.kch.likelion.LikeLionKing.post.domain.Post;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;


@Repository
public class JdbcPostRepository implements PostRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcPostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Post insert(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO posts(title,content) VALUES (?,?)", new String[]{"postSeq"});
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long generatedSeq = key != null ? key.longValue() : -1;
        return new Post.Builder(post)
                .postSeq(generatedSeq)
                .build();
    }

    @Override
    public void update(Post post) {
        jdbcTemplate.update(
                "UPDATE posts SET title=?,content=? WHERE postSeq=?",
                post.getTitle(),
                post.getContent(),
                post.getPostSeq()
        );
    }

    @Override
    public void delete(long postId) {
        jdbcTemplate.update(
                "DELETE FROM POSTS WHERE postSeq=?",
                postId
        );
    }

    public List<Post> findAll() {
        List<Post> results = jdbcTemplate.query(
                "SELECT p.* FROM posts p",
                mapper
        );
        return results;
    }
    static RowMapper<Post> mapper = (rs, rowNum) -> new Post.Builder()
            .postSeq(rs.getLong("postSeq"))
            .title(rs.getString("title"))
            .content(rs.getString("content"))
            .build();
}
