package com.thlim.book.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


// 해당 클래스는 앞으로의 프로젝트의 메인 클래스가 됨.
// 아래 어노테이션에 의해 스프링 부트 설정, bean 읽기와 생성 모두 자동 설정.
// 해당 클래스 위치부터 설정을 읽어가기 때문에 항상 프로젝트 최상단에 위치해야 함.

@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 내장 was 실행
        // 언제 어디서나 같은 환경에서 스프링부트 배포 가능.
        // 외장 와스를 쓰면 모든 서버는 was 종류, 버전 설정을 일치시켜야 함.
        // 톰캣 역시 서블릿으로 이루어진 자바 애플리케이션
        // jdbc:h2:mem:testdb
        // http://localhost:8080/h2-console
        // insert into posts (author, content, title) values('author', 'content', 'title')
        // http://localhost:8080/api/v1/posts/1

        SpringApplication.run(Application.class,args);
    }

}
