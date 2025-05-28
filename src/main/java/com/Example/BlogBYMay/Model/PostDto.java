package com.example.BlogBYMay.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    private Long postId;

    private String fullName;

    private String title;

    private String description;

    private String image;
}
