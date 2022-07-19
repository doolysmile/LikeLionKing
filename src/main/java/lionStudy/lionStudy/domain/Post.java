package lionStudy.lionStudy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Post {

    private Long id; // 식별자
    private Long userId; // 작성자 ID
    private String title; // 제목
    private String content; // 내용

    private Long views; // 조회수
    private Long recommended; // 추천수

    public Post(){}

}
