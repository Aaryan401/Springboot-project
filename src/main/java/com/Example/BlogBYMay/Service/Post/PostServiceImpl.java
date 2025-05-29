package com.example.BlogBYMay.Service.Post;

import com.example.BlogBYMay.Entity.Post;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Model.PostDto;
import com.example.BlogBYMay.Repository.PostRepository;
import com.example.BlogBYMay.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new RuntimeException("Post not found"));
        PostDto postDto = PostDto.builder()
                .fullName(post.getUser().getFirstName()+" "+post.getUser().getLastName())
                .title(post.getTitle())
                .description(post.getDescription())
                .image(post.getImage())
                .build();
        return postDto;
    }
    @Override
    public List<PostDto> getAllPostOfUser(Long userId) {
        List<PostDto> listDto = new ArrayList<>();
        User foundUser = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        List<Post> posts = foundUser.getPosts();
        for(Post post:posts){
            PostDto postDto = PostDto.builder()
                    .postId(post.getPostId())
                    .fullName(post.getUser().getFirstName()+" "+post.getUser().getLastName())
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .image(post.getImage())
                    .build();
            listDto.add(postDto);

        }
        return listDto;
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> post=postRepository.findAll();
        return post;
    }



    @Override
    public String updatePost(Long userId, PostDto postDto) {
        Post post= postRepository.findByUserUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        String [] str=postDto.getFullName().split(" ");
        post.getUser().setFirstName(str[0]);
        post.getUser().setLastName(str[1]);
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setImage(postDto.getImage());
        postRepository.save(post);
        return "post has been updated successfully";
    }

    @Override
    public String deletePost(Long postId) {
        postRepository.deleteById(postId);
        return "Your post has been deleted successfully";
    }
}
