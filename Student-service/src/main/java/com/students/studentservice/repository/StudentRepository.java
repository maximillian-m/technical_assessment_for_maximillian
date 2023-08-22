package com.students.studentservice.repository;

import com.students.studentservice.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByEmailAddress(String email);
}
