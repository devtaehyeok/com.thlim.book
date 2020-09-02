package com.thlim.book.springboot.domain.posts;

import com.thlim.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 주요 어노테이션을 클래스에 가깝게
// 실제 DB와 매칭될 클래스
// 기본값으로 클래스의 카멜케이스 이름을 언도스코어 네이밍으로 테이블 이름 매칭
@Getter
@NoArgsConstructor // lombok 기본 생성자 자동 추가 코틀린 필요없으면 제거
@Entity // jpa
public class Posts extends BaseTimeEntity {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙. AUTO INCREMENT 의미
    private Long id;
    /* pk를 LONG TYPE의 AUTO_INCREMENT로 하는 이유
     * 1. FK를 맻을 때, 다른 테이블에서 복합키 전부를 갖고 있거나, 중간 테이블을 하나 두는 상황 발생
     * 2. 인덱스에 악영향
     * 3. 유니크 조건 변경 시 PK 전체 수정
     * 4. 주민등록번호, 복합키 등은 유니크 키로 별도 추가.
     *
     *
     * ENTITY 세터 사용 X 목적과 의도 알 수 있는 메서드를 선언
     * ex cancelOrder(){
     *  this.status = "cancel";
     * }
     * 주문서비스의_취소이벤트(){
     *  order.cancelOrder();
     * }
     */

    @Column(length = 500, nullable = false)
    private String title;

    // 테이블의 컬럼을 나타냄
    // 굳이 안해도 전부 다 컬럼처리
    // 문자열 기본값 255 늘리거나
    // 타입을 TEXT로 변경
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스 생성 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
