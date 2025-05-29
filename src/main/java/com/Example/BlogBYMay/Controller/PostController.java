package com.example.BlogBYMay.Controller;

import com.example.BlogBYMay.Entity.Post;
import com.example.BlogBYMay.Model.PostDto;
import com.example.BlogBYMay.Service.Post.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getPostById/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="postId") Long postId){
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("getAllPost")
    public ResponseEntity<List<Post>> getAllPost(){
        List<Post> post = postService.getAllPost();
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @PutMapping("updatePost/{userId}")
    public ResponseEntity<String> updatePost(@PathVariable(name="userId") Long userId, @RequestBody PostDto postDto){
        String response = postService.updatePost(userId,postDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("deletePost/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(name="postId") Long postId){
        String response = postService.deletePost(postId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
