package com.ccdev.courseManagement.controller;

import com.ccdev.courseManagement.entity.Course;
import com.ccdev.courseManagement.repository.CourseRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
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

    @GetMapping("/courses/new")
        public String addCourse(Model model){
            List<String> semesters = Arrays.asList("Spring","Autumn");
        Course course = new Course();
        course.setPublished(true);
        model.addAttribute("course", course);
        model.addAttribute("pageTitle","New Course");
        model.addAttribute("semesters", semesters);
        return "course_form";
    }

    @PostMapping("/courses/save")
    public String saveCourse(Course course, RedirectAttributes redirectAttributes){
        try {
            courseRepository.save(course);
            redirectAttributes.addFlashAttribute("message", "the course has been saved successfully.");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/courses";
    }

    @GetMapping("/courses/{id}")
    public String editCourse(@PathVariable Integer id,Model model, RedirectAttributes redirectAttributes){
        try {
            Course course = courseRepository.findById(id).get();
            model.addAttribute("course",course);

            model.addAttribute("pageTitle","Edit course of id "+id);

            List<String> semesters = Arrays.asList("Spring","Autumn");
            model.addAttribute("semesters", semesters);

            return "course_form";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try{
            courseRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "the course has been deleted successfully.");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/courses";
    }

}
