package com.example.BlogBYMay.Service.Course;

import com.example.BlogBYMay.Entity.Course;
import com.example.BlogBYMay.Entity.User;
import com.example.BlogBYMay.Model.StudentCourseDto;
import com.example.BlogBYMay.Repository.CourseRepository;
import com.example.BlogBYMay.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseServiceInterface {

    @Autowired
    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    @Override
    public String SaveCourse(Course course) {
        courseRepository.save(course);
        return "Course Saved";
    }

    @Override
    public List<Course> getCourse(Long courseId) {
        List<Course> courseList = courseRepository.findAll();
        return courseList;
    }

    public StudentCourseDto getAllCourseOfUser(Long userId){
        User founduser = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        Set<Course> courses = founduser.getCourses();
        StudentCourseDto studentCourseDto = StudentCourseDto.builder()
                .fullName(founduser.getFirstName()+" "+founduser.getLastName())
                .course(courses.stream().toList()).build();
            return studentCourseDto;
    }
}
