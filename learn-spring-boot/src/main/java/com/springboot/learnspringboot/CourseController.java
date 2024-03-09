package com.springboot.learnspringboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {
    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses(){
        return Arrays.asList(
                new Course(1L,"DSA","maiden"),
                new Course(2L,"Java","ram"),
                new Course(2L,"Java Spring","laxman"),
                new Course(2L,"Java Spring","laxman"),
                new Course(2L,"Java Spring","laxman")
        );
    }
}
