package com.thlim.book.springboot.web.dto;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        // given
        String name = "test";
        int amount = 100;
        // when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        // coreMAtcher같이 추가 라이브러리 불필여
        // 자동완성이 잘됨.
        // https://bit.ly/30vm9Lg
    }

}