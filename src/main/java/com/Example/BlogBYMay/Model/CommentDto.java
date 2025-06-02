package com.example.BlogBYMay.Model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {

    @NotBlank(message = "Post Id must not be blank")
    public Long commentId;

    @NotBlank(message = "Full Name must not be blank")
    @Pattern(regexp="^[A-Za-z]+$", message = "Full Name must contain only alphabet")
    public String fullName;

    @NotBlank(message = "Comment must not be blank")
    public String comment;
}
