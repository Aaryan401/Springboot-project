package com.example.BlogBYMay.Model;

import com.example.BlogBYMay.Entity.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentCourseDto {

    private Long courseId;

    @NotBlank(message = "Full name must cannot be Blank")
    @Pattern(regexp="^[A-Za-z]+$", message = "Full Name must contain only alphabet")
    private String fullName;

    private List<Course> course;


}
