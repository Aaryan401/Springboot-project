package com.example.BlogBYMay.Repository;

import com.example.BlogBYMay.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    public Optional<Comment> findByPostPostId(Long postId);
}
