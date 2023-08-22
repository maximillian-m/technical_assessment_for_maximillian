package com.courses.coursesservice.repository;

import com.courses.coursesservice.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Courses, Long> {
    @Query("SELECT c FROM Courses c WHERE c.courseId = ?1")
    Optional<Courses> findByCourseId(String courseId);
}
