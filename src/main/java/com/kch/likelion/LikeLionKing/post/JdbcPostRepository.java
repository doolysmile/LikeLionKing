package com.kch.likelion.LikeLionKing.post;


import com.kch.likelion.LikeLionKing.post.domain.BoardType;
import com.kch.likelion.LikeLionKing.post.domain.Post;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;


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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO posts(userSeq,views,likes,title,content,boardType,creatAt) VALUES (?,?,?,?,?,?,?)", new String[]{"postSeq"});
            ps.setLong(1, post.getUserSeq());
            ps.setInt(2, post.getViews());
            ps.setInt(3, post.getLikes());
            ps.setString(4, post.getTitle());
            ps.setString(5, post.getContent());
            ps.setInt(6, post.getBoardType().value());
            ps.setTimestamp(7, Timestamp.valueOf(post.getCreateAt()));
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

    public List<Post> findAll(int boardType, int offset, int limit, String searchKeyword) {
        List<Post> results = jdbcTemplate.query(
                "SELECT p.* FROM posts p WHERE p.boardType=? and p.title LIKE ? ORDER BY p.postSeq LIMIT ?,?",
                mapper,
                '%'+ searchKeyword + '%',
                boardType,
                offset,
                limit
        );
        return results;
    }

    @Override
    public Optional<Post> findById(Long id) {
        List<Post> posts = jdbcTemplate.query(
                "SELECT p.* FROM posts p WHERE postSeq=?",
                mapper,
                id
        );
        return ofNullable(posts.isEmpty() ? null : posts.get(0));
    }

    @Override
    public void increaseViews(Post post) {
        jdbcTemplate.update(
                "UPDATE posts SET views=? WHERE postSeq=?",
                post.getViews(),
                post.getPostSeq()
        );
    }

    static RowMapper<Post> mapper = (rs, rowNum) -> new Post.Builder()
            .postSeq(rs.getLong("postSeq"))
            .userSeq(rs.getLong("userSeq"))
            .views(rs.getInt("views"))
            .likes(rs.getInt("likes"))
            .title(rs.getString("title"))
            .content(rs.getString("content"))
            .boardType(BoardType.valueOf(rs.getInt("boardType")))
            .createAt(rs.getTimestamp("creatAt").toLocalDateTime())
            .build();
}
