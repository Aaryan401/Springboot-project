package com.example.BlogBYMay.Service.Comment;


import com.example.BlogBYMay.Entity.Comment;
import com.example.BlogBYMay.Entity.Post;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Model.CommentDto;
import com.example.BlogBYMay.Repository.CommentRepository;
import com.example.BlogBYMay.Repository.PostRepository;
import com.example.BlogBYMay.Repository.UserRepository;
import com.example.BlogBYMay.Service.Comment.CommentServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentServiceInterface {
    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PostRepository postRepository;

    @Override
    public String saveComment(Long postId,Long userId, Comment comment) {
        Post postFound = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        User userFound = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        comment.setPost(postFound);
        comment.setUser(userFound);
        commentRepository.save(comment);
        return "Your have successfully post Comment";
    }

    @Override
    public List<CommentDto> getCommentById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post Not found"));
        List<CommentDto> commentDtoList = new ArrayList<>();
        List<Comment> comments = post.getComments();

        for (Comment comment:comments) {
            CommentDto commentDto = CommentDto.builder()
                    .commentId(comment.getCommentId())
                    .fullName(comment.getUser().getFirstName()+" "+comment.getUser().getLastName())
                    .comment(comment.getComment())
                    .build();
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    @Override
    public String updateComment(Long postId, CommentDto commentDto) {
        Comment comment=commentRepository.findByPostPostId(postId).orElseThrow(()->new RuntimeException("No Comment found"));
        comment.setComment(commentDto.getComment());
        commentRepository.save(comment);
        return "Your comment has been edited successfully";
    }

    @Override
    public String deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return "Your comment has been deleted successfully";
    }
}
