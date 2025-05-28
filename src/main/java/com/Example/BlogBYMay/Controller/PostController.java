package com.example.BlogBYMay.Controller;

import com.example.BlogBYMay.Entity.Post;
import com.example.BlogBYMay.Service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private final PostServiceImpl postService;

    @PostMapping("post/{userId}")
    public ResponseEntity<String> crtatePost(@PathVariable(name="userId") Long userId, @RequestBody Post post){
        String response = postService.savePost(userId,post);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
