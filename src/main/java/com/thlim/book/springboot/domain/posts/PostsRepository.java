package com.thlim.book.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// DAO라 불리는 DB LAYER 접근자
// JPA에서는 REPOSITORY라고 불리며 인터페이스로 생성 후 JpaRepository 상속
// REPOSITORY를 추가할 필요도 없음
// 주의할 점은 Entity와 REPO가 동시에 위치해야 하는 것
// 둘은 함께 움직여야 하므로 도메인 패키지에서 동시 관리
public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("SELECT p FROM Posts p order by p.id DESC")
    List<Posts> findAllDesc();
}
