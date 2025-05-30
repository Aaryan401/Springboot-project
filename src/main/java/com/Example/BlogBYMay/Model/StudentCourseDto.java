package com.example.BlogBYMay.Model;

import com.example.BlogBYMay.Entity.Course;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentCourseDto {

    private Long courseId;

    private String fullName;

    private List<Course> course;


}
