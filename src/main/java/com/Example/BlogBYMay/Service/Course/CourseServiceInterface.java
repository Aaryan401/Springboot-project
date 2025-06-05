package com.example.BlogBYMay.Service.Course;

import com.example.BlogBYMay.Entity.Course;

import java.util.List;

public interface CourseServiceInterface {
    public String SaveCourse(Course course);
    public List<Course> getCourse(Long courseId);
}
