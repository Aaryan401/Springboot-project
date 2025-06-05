package com.example.BlogBYMay.Controller;


import com.example.BlogBYMay.Entity.Comment;
import com.example.BlogBYMay.Model.CommentDto;
import com.example.BlogBYMay.Service.Comment.CommentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private final CommentServiceImpl commentService;

    @PostMapping("comment/{userId}/{postId}")
    public ResponseEntity<String> createComment(@PathVariable(name="postId") Long postId,@PathVariable(name= "userId") Long userId,@Valid @RequestBody Comment comment){
        String response=commentService.saveComment(postId,userId,comment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getCommentById/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentById(@PathVariable(name="postId") Long postId){
        List<CommentDto> commentDto=commentService.getCommentById(postId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @PutMapping("updateComment/{postId}")
    public ResponseEntity<String> updateComment(@PathVariable(name="postId") Long postId,@Valid @RequestBody CommentDto commentDto){
        String response=commentService.updateComment(postId,commentDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("deleteComment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name="commentId") Long commentId){
        String response=commentService.deleteComment(commentId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
