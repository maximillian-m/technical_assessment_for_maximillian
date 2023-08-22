package com.students.studentservice.repository;

import com.students.studentservice.models.Student;
import com.students.studentservice.models.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    @Query("select c.student from StudentCourse c WHERE c.courseId = ?1")
    Optional<List<Student>> findStudentByCourseId(String id);

    @Query(value = "SELECT * FROM student_course WHERE student_id = :studentId", nativeQuery = true)
    List<StudentCourse> findByStudentId(@Param("studentId") Long studentId);
}
