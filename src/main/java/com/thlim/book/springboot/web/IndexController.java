package com.thlim.book.springboot.web;

import com.thlim.book.springboot.domain.posts.PostService;
import com.thlim.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostService postService;
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts",postService.findAllDesc());
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto responseDto = postService.findById(id);
        model.addAttribute("post",responseDto);
        return "posts-update";
    }

    @DeleteMapping("/posts/update/{id}")
    public Long postDelete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }


}
