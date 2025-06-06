package com.Example.BlogByMay.Service.User.Post;

import com.example.BlogBYMay.Entity.Post;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Repository.PostRepository;
import com.example.BlogBYMay.Repository.UserRepository;
import com.example.BlogBYMay.Service.Post.PostServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    public PostRepository postRepository;

    @Mock
    public UserRepository userRepository;

    @InjectMocks
    public PostServiceImpl postService;

    private Post post;
    private User user;
    private Long userId;
    @BeforeEach
    public void setUp(){

        user = User.builder()
                .userId(userId)
                .build();


        post = Post.builder()
                .postId(1L)
                .title("Sun")
                .description("Sun rise from east")
                .image("image.jpg")
                .user(user)
                .build();
    }


    @AfterEach
    public void tearDown(){
        post=null;
    }


    @Test
    public void testSavePost(){
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        String response = postService.savePost(userId, post);

        assertThat(response).isEqualTo("Your Post has been saved");
        System.out.println("Test passed");

    }
}
