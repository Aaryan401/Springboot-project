package com.example.BlogBYMay.Service.Comment;

import com.example.BlogBYMay.Entity.Comment;
import com.example.BlogBYMay.Model.CommentDto;

import java.util.List;

public interface CommentServiceInterface {
    public String saveComment(Long postId, Long userId, Comment comment);
    public List<CommentDto> getCommentById(Long commentId);
    public String updateComment(Long postId, CommentDto commentDto);
    public String deleteComment(Long commentId);
}
