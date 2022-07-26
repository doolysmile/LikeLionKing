package lionStudy.lionStudy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Post {

    private Long id; // 식별자
    private Long userId; // 작성자 ID, 외래키
    private String title; // 제목
    private String content; // 내용

    private Long views; // 조회수
    private Long recommended; // 추천수

    public Post(){}

    public Post(Long id, Long userId, String title, String content, Long views, Long recommended) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.views = views;
        this.recommended = recommended;
    }


    static public class Builder {

        private Long id; // 식별자
        private Long userId; // 작성자 ID, 외래키
        private String title; // 제목
        private String content; // 내용

        private Long views; // 조회수
        private Long recommended; // 추천수

        public Builder(){}

        public Builder(Long id, Long userId, String title, String content, Long views, Long recommended) {
            this.id = id;
            this.userId = userId;
            this.title = title;
            this.content = content;
            this.views = views;
            this.recommended = recommended;
        }

        public Post build() {
            return new Post(id,userId,title,content,views,recommended);
        }


    }
}
