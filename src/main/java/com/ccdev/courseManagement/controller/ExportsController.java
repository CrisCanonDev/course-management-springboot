package com.ccdev.courseManagement.controller;

import com.ccdev.courseManagement.entity.Course;
import com.ccdev.courseManagement.reports.ExportCoursesPDF;
import com.ccdev.courseManagement.repository.CourseRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ExportsController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/export/pdf")
    public void generateExportPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentTimeFormat = dateFormat.format(new Date());

        String headerKey ="Content-Disposition";
        String headerValue = "attachment; filename=courses"+currentTimeFormat+".pdf";
        response.setHeader(headerKey, headerValue);

        List<Course> courses = courseRepository.findAll();

        ExportCoursesPDF exportCoursesPDF = new ExportCoursesPDF(courses);
        exportCoursesPDF.export(response);
    }
}
