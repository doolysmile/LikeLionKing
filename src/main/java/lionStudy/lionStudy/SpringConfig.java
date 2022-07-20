package lionStudy.lionStudy;

import lionStudy.lionStudy.repository.JdbcTemplateMemberRepository;
import lionStudy.lionStudy.repository.JdbcTemplatePostRepository;
import lionStudy.lionStudy.repository.MemberRepository;
import lionStudy.lionStudy.repository.PostRepository;
import lionStudy.lionStudy.service.MemberService;
import lionStudy.lionStudy.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PostService postService()  {
        return new PostService(postRepository());
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }

    @Bean
    public PostRepository postRepository() {
        return new JdbcTemplatePostRepository(dataSource);
    }
}
