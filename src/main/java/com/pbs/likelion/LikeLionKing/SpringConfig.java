package com.pbs.likelion.LikeLionKing;

import com.pbs.likelion.LikeLionKing.repository.JdbcTemplateMemberRepository;
import com.pbs.likelion.LikeLionKing.repository.JdbcTemplatePostRepository;
import com.pbs.likelion.LikeLionKing.repository.MemberRepository;
import com.pbs.likelion.LikeLionKing.repository.PostRepository;
import com.pbs.likelion.LikeLionKing.service.MemberService;
import com.pbs.likelion.LikeLionKing.service.PostService;
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
