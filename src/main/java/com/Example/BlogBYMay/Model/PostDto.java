package com.example.BlogBYMay.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    private Long postId;

    @NotBlank(message = "Full Name must not be blank")
    @Pattern(regexp="^[A-Za-z]+(?:\s[A-za-z]+)*$", message = "Full Name must contain only alphabet")
    private String fullName;

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotBlank(message = "Image must not be blank")
    private String image;
}
