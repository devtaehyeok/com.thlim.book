package com.thlim.book.springboot.web;

import com.thlim.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * // @RestController :컨트롤러를 json을 반환하게 해줌
 * 예전에는 @ResponseBody를 각 메소드마다 선언했음
 */
public class HelloController {
    /**
     * http method get 요청을 받을 수 있는 API를 만들어 줌.
     * 예전에는 @RequestMapping(method=ResponseMethod.GET)으로 사용
     * /hello 요청이 오면 문자열 hello를 반환한다.
     *
     * @return "hello"
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
