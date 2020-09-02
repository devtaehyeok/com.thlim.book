package com.thlim.book.springboot.domain.posts;

import com.thlim.book.springboot.web.PostsResponseDto;
import com.thlim.book.springboot.web.PostsUpdateRequestDto;
import com.thlim.book.springboot.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    // 가장 @Autowired setter 생성자 중 생성자가 최고
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    // update 부분에 쿼리 날리는 부분이 없음
    // 영속성 컨텍스트 때문임
    // 엔티티 영구저장 환경
    // 일종의 논리적 개념. JPA의 핵심은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts post = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        post.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }
    /*
    클래스, 메서드위에 @Transactional 이 추가되면, 이 클래스에 트랜잭션 기능이 적용된 프록시 객체가 생성된다.
    이 프록시 객체는 @Transactional이 포함된 메소드가 호출 될 경우, PlatformTransactionManager를 사용하여 트랜잭션을 시작하고, 정상 여부에 따라 Commit 또는 Rollback 한다.
    출처: https://goddaehee.tistory.com/167 [갓대희의 작은공간]
     */
    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "= id에 해당하는 게시글이 없습니다."));
        return new PostsResponseDto(entity);
    }
}
