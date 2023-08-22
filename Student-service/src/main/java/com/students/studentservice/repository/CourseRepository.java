package com.students.studentservice.repository;

import com.students.studentservice.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Courses, Long> {
    Optional<Courses> findCoursesByCourseId(String courseId);
}
