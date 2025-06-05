package com.example.BlogBYMay.Service.Post;

import com.example.BlogBYMay.Entity.Post;
import com.example.BlogBYMay.Model.PostDto;
import java.util.List;

public interface PostServiceInterface {
    public String savePost(Long userId, Post post);
    public PostDto getPostById(Long postId);
    public List<Post> getAllPost();
    public List<PostDto> getAllPostOfUser(Long userId);
    public String updatePost(Long userId,Long postId, PostDto postDto);
    public String deletePost(Long postId);
}
