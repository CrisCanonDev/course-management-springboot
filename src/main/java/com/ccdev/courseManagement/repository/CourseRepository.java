package com.ccdev.courseManagement.repository;

import com.ccdev.courseManagement.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Page<Course> fingTitleIgnoringCase(String keyword, Pageable pageable);
}
