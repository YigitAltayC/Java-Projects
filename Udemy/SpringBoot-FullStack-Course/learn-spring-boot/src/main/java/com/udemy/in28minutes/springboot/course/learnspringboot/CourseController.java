package com.udemy.in28minutes.springboot.course.learnspringboot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    // courses
    // Course: id, name, author

    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses() {
        return Arrays.asList(
                new Course(1, "Learn AWS", "AWS-Developer Platform"),
                new Course(2, "Learn DevOps", "Patika-Dev"),
                new Course(3, "Unity Learn Patikası", "Patika-Dev")
        );
    }
}
