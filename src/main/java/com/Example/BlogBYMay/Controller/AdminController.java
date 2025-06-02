package com.example.BlogBYMay.Controller;


import com.example.BlogBYMay.Entity.Course;
import com.example.BlogBYMay.Service.Course.CourseServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final CourseServiceImpl courseService;

    @PostMapping("saveCourse")
    public ResponseEntity<String> saveCourse(@Valid @RequestBody Course course){
        String response = courseService.SaveCourse(course);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getCourse/{courseId}")
    public ResponseEntity<List<Course>> getCourse(@PathVariable(name="courseId") Long courseId){
        List<Course> courses=courseService.getCourse(courseId);
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }
}
