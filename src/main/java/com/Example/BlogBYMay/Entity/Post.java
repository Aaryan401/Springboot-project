package com.example.BlogBYMay.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users_posts")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    @NotBlank(message = "Title must not be blank")
    private String title;

    @Column(nullable = false,columnDefinition = "TEXT")
    @NotBlank(message = "Description must not be blank")
    private String description;

    @Column(nullable = false)
    @NotBlank(message = "Image must not be blank")
    private String image;

    @Column(nullable = false,updatable=false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate=LocalDateTime.now();
        this.updatedDate=LocalDateTime.now();
    }

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @PreUpdate
    protected void onUpdate(){
        this.updatedDate=LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy ="post", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments=new ArrayList<>();
}
