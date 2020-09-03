package com.thlim.book.springboot.web.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter // 모든 필드의 게터 생성해줌
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int  amount;
}