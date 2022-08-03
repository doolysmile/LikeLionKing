package com.pbs.likelion.LikeLionKing.repository;

import com.pbs.likelion.LikeLionKing.domain.Post;
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
    public Post save(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("categoryId", post.getCategoryId());
        parameters.put("userId", post.getUserId());
        parameters.put("title", post.getTitle());
        parameters.put("content", post.getContent());
        parameters.put("views", post.getViews());
        parameters.put("recommended", post.getRecommended());
        parameters.put("createdAt", post.getCreatedAt());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        post.setId(key.longValue());
        return post;
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

//    내용 : 1번 게시판(공지사항)의 게시물 10개 노출, 1 페이지
    @Override
    public List<Post> find_LatestPosts10(int categoryId) {
        return jdbcTemplate.query("select * from post where categoryId = ? limit 10", postRowMapper(), categoryId);
    }

//    내용 : 2번 게시판(자유게시판)의 게시물 10개 노출, 1 페이지
    @Override
    public List<Post> findByCategory_WithPageNum(int categoryId, int pageNum) {
        // limit 시작점, 갯수 (아래 예의 경우 5번째부터 10개 추출, 첫번째 파라미터는 0 부터 시작 )
        // select * from {table_name} limit 4, 10;
        return jdbcTemplate.query("select * from post where category_id = ? limit ?, 10", postRowMapper(), categoryId, (pageNum - 1) * 10);
    }

//    내용 : 2번 게시판(자유게시판)의 게시물 중에서, 제목에 11이라는 문장이 포함된 것들만 추려서 10개 노출
    @Override
    public List<Post> findByCategory_WithKeyword(int categoryId, String keyword) {
        return jdbcTemplate.query("select * from post where categoryId = ? and title like ? limit 10", postRowMapper(), categoryId, keyword + "%");
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
            post.setCategoryId(rs.getInt("categoryId"));
            post.setUserId(rs.getLong("userId"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            post.setViews(rs.getLong("views"));
            post.setRecommended(rs.getLong("recommended"));
            post.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());

            return post;

        };
    }
}
