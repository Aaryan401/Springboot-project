package com.example.BlogBYMay.Service;

import com.example.BlogBYMay.Entity.Post;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Repository.PostRepository;
import com.example.BlogBYMay.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostServiceInterface {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public String savePost(Long userId, Post post) {
        User foundUser= userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        post.setUser(foundUser);
        postRepository.save(post);
        return "Your Post has been saved";
    }
}
