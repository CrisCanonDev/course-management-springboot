package com.ccdev.courseManagement.controller;

import com.ccdev.courseManagement.entity.Course;
import com.ccdev.courseManagement.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public String home(){
        return "redirect:/courses";
    }

    @GetMapping("/courses")
    public String listCoursers(Model model){
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "courses";
    }
}
