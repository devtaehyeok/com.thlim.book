package com.thlim.book.springboot.web.dto;

import com.thlim.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Entitiy를 절대로 Request / Response 클래스로 사용하면 안된다!
// Req/ Res용 DTO는 View를 위한 클래스라 정말 자주 변경이 된다.
// 실제 컨트롤러에서 조인 자주 필요한 경우가 많으므로 Entitiy만으로 표현 어려울 수 있다.
// 꼭 ENtity // Controller DTO는 분리하자!
@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    private PostSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }
}
