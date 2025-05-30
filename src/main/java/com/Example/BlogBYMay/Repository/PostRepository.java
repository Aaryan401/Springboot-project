package com.example.BlogBYMay.Repository;

import com.example.BlogBYMay.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{
    public Optional<Post> findByUserUserId(Long userId);
}
