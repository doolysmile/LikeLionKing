package com.hsy.likelion.LikeLionKing.repository;

import com.hsy.likelion.LikeLionKing.domain.Post;
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
public class JdbcTemplatePostRepository implements PostRepository{
    private final JdbcTemplate jdbcTemplate;

    // DataSource: DB와 관계된 Connection 정보(Application.properties에서 빈으로 등록됨)
    // dataSource를 의존성 주입받아 JdbcTemplate의 인자로 넘김
    // 생성자 1개면 @Autowired 생략O
    public JdbcTemplatePostRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Create
    @Override
    public Post save(Post post) {
        // 테이블명, pk, 컬럼 정보 -> insert문 자동 생성
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("id");
        // 파라미터
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", post.getTitle());
        parameters.put("content", post.getContent());
        // DB에서
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        post.setId(key.longValue());
        return post;    // 게시글 반환
    }

    // 해당 id의 post를 Optional로 반환하는 메서드
    @Override
    public Optional<Post> findById(Long id) {
        List<Post> result = jdbcTemplate.query("select * from post where id = ?", postRowMapper(), id);
        return result.stream().findAny();   // 가장 먼저 탐색되는 요소를 Optional로 리턴
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("select * from post", postRowMapper());
    }

    // post update
    @Override
    public void update(Post post) {
        jdbcTemplate.update("update post set title = ?, content = ? where id = ?", post.getTitle(), post.getContent(), post.getId());
    }

    // 해당 id의 post delete
    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from post where id = ?", id);
    }

    // 쿼리 결과를 RowMapper로 매핑하여 원하는 자바 객체로 변환하는 메서드
    // 쿼리 결과값(Row 값)들을 RowMapper를 이용해 ResultSet -> 자바 객체로 변환
    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> {
            Post post = new Post();
            // "컬럼명"으로 해당 타입 데이터를 받아와 Post 객체로 반환
            post.setId(rs.getLong("id"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            return post;
        };
    }
}