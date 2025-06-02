package com.example.BlogBYMay.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Comment must not be blank")
    private String comment;

    @Column(nullable = false,updatable = false)
    private LocalDateTime commentDate;

    @PrePersist
    protected void onComment(){
        this.commentDate=LocalDateTime.now();
        this.updatedCommentDate = LocalDateTime.now();
    }

    @Column(nullable = false)
    private LocalDateTime updatedCommentDate;

    @PreUpdate
    protected void onUpdate(){
        this.updatedCommentDate = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
