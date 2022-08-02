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
    public Long save(Post post) {
        //jdbcTemplate.update("insert into post(member_id, category_id, title, content) values(?, ?, ?, ?)", post.getMemberId(), post.getCategoryId(), post.getTitle(), post.getContent());
        // 테이블명, pk, 컬럼 정보 -> insert문 자동 생성
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert
                .withTableName("post")  // 테이블명
                .usingColumns("member_id", "category_id", "title", "content")   // 컬럼정보(insert할 때 사용할 값만, 지정안하면 디폴트로 모든값 대상)
                .usingGeneratedKeyColumns("id");

        // 컬럼에 값 넣기
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("member_id", post.getMemberId());
        parameters.put("category_id", post.getCategoryId());
        parameters.put("title", post.getTitle());
        parameters.put("content", post.getContent());

        // DB에 insert 후 pk 자동 반환
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        post.setId(key.longValue());

        return post.getId();    // 게시글 id 반환
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

    // 해당 카테고리 게시글 상위 10개 조회
    @Override
    public List<Post> findByCategoryId(Integer categoryId) {
        return jdbcTemplate.query("select * from post where category_id = ? limit 10", postRowMapper(), categoryId);
    }

    // 해당 카테고리의 n번 페이지 게시글 10개 조회
    @Override
    public List<Post> findByCategoryPage(Integer categoryId, Integer page) {
        return jdbcTemplate.query("select * from post where category_id = ? limit ?, 10", postRowMapper(), categoryId, (page - 1) * 10);
    }

    // 해당 카테고리에서 검색어로 시작하는 게시글 10개 조회
    @Override
    public List<Post> findByCategorySearchAll(Integer categoryId, String search) {
        return jdbcTemplate.query("select * from post where category_id = ? and title like ? limit 10", postRowMapper(), categoryId, search + "%");
    }

    @Override
    public void increaseViews(Long id) {
        jdbcTemplate.update("update post set views = views + 1 where id = ?", id);
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
            // "컬럼명"으로 해당 타입 데이터를 받아와 Post 객체로 반환
            Post post = Post.builder()
                    .id(rs.getLong("id"))
                    .memberId(rs.getLong("member_id"))
                    .categoryId(rs.getInt("category_id"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .createdAt(rs.getString("created_at"))
                    .updatedAt(rs.getString("updated_at"))
                    .views(rs.getLong("views"))
                    .likes(rs.getLong("likes"))
                    .build();

            return post;
        };
    }
}