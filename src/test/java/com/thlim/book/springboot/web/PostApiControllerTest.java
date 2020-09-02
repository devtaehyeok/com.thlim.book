package com.thlim.book.springboot.web;

import com.thlim.book.springboot.domain.posts.Posts;
import com.thlim.book.springboot.domain.posts.PostsRepository;
import com.thlim.book.springboot.web.dto.PostSaveRequestDto;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// webMVC 테스트는 JPA가 작동안함 ㅠ
// Controller, ControllerAdvice 등 외부 연동 기능만 작동함
// SpringBootTest와 TestRestTemplate를 같이 사용하면 됨
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void teardown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void BaseTimeEntity_등록() {
        String title = "title";
        String content = "content";
        String author = "author";
        // given
        LocalDateTime now = LocalDateTime.of(2020, 9, 3, 0, 0, 0);
        postsRepository.save(Posts.builder().title(title).author(author).content(content).build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then

        Posts posts = postsList.get(0);
        System.out.println(posts.getCreatedDate() + " :  생성일자, " + posts.getModifiedDate() + " : 수정일자");
        assertThat(posts.getCreatedDate().isAfter(now));
        assertThat(posts.getModifiedDate().isAfter(now));
    }

    @Test
    public void Posts_등록() throws Exception {
        // given
        String title = "title";
        String content = "content";
        String author = "author";

        PostSaveRequestDto requestDto = PostSaveRequestDto.builder().author(author)
                .content(content)
                .title(title).build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        // 세번째 인자는 리턴 클래스 타입
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_수정() throws Exception {
        // given

        String title = "title";
        String content = "content";
        String author = "author";

        Posts savedPosts = postsRepository.save(Posts.builder().title(title).content(content).author(author).build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder().title(expectedTitle).content(expectedContent).build();
        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);


    }
}