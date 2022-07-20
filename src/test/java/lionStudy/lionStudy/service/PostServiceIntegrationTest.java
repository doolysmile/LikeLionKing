package lionStudy.lionStudy.service;

import lionStudy.lionStudy.domain.DTO.PostDto;
import lionStudy.lionStudy.domain.Member;
import lionStudy.lionStudy.domain.Post;
import lionStudy.lionStudy.repository.MemberRepository;
import lionStudy.lionStudy.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

class PostServiceIntegrationTest {


    private Long id = 2L;
    @Autowired PostService postService;
    @Autowired
    PostRepository postRepository;

    /**
     * CRUD - C
     */
    @Test
    public void 게시글_등록() throws Exception {
        //Given
        PostDto post = new PostDto();
        post.setUserId(1L);
        post.setTitle("test2");
        post.setContent("testContent");
        post.setViews(30L);
        post.setContent("content2");
        //When
        Long saveId = postService.register(post);
        //Then
        Post findPost = postRepository.findById(saveId).get();
        assertEquals(post.getTitle(), findPost.getTitle());
    }

    /**
     * CRUD-U
     * 게시글 제목수정
     */
    @Test
    public void 게시글_제목수정ById() throws Exception {
        postService.updateTitleById("updateTitle", id);
    }

    /**
     * CRUD-U
     * 게시글 내용수정
     */
    @Test
    public void 게시글_내용수정ById() throws Exception {
        postService.updateContentById("updateContent", id);
    }

    /**
     * CRUD-U
     * 게시글 전체수정
     */
    @Test
    public void 게시글_수정ById() throws Exception {
        postService.updateById("updateTitle", "updateContent", id);
    }
    /**
     * CRUD - D
     */
    @Test
    public void 게시글_삭제ById() throws Exception {
        postService.deleteById(id);
    }

    @Test
    public void 게시글_모두조회() throws Exception {
        List<Post> posts = postService.findPosts();
        for (Post post : posts) {
            System.out.println("post_Id :" + post.getId() + "       post.getTitle() : " + post.getTitle() + "      post.getContent() : " + post.getContent());

        }
    }



    @Test
    public void 중복_회원_예외() throws Exception {
//        //Given
//        Member member1 = new Member();
//        member1.setName("spring");
//        Member member2 = new Member();
//        member2.setName("spring");
//        //When
//        memberService.join(member1);
//        IllegalStateException e = assertThrows(IllegalStateException.class,
//                () -> memberService.join(member2));//예외가 발생해야 한다.
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}