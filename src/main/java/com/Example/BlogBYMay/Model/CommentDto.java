package com.example.BlogBYMay.Model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    public Long commentId;

    public String fullName;

    public String comment;
}
