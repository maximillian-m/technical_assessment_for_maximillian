package com.students.studentservice.controller;

import com.students.studentservice.dto.requests.StudentRegistrationRequest;
import com.students.studentservice.dto.requests.UpdateCourseByStudent;
import com.students.studentservice.exceptions.CustomException;
import com.students.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationRequest request) throws CustomException {
        return new ResponseEntity<>(service.createStudent(request), HttpStatus.CREATED);
    }

    @GetMapping("/getListOfCourses")
    public ResponseEntity<?> getListOfCourses() {
        return new ResponseEntity<>(service.getListOfcourse(), HttpStatus.FOUND);
    }

    @GetMapping("/select-course/{courseId}")
    public ResponseEntity<?> allocateCourse(@PathVariable String courseId) throws CustomException {
        return new ResponseEntity<>(service.allocateCourse(courseId), HttpStatus.OK);
    }

    @PutMapping("/update-course/{courseId}")
    public ResponseEntity<?> updateCourseByStudent(@RequestBody UpdateCourseByStudent updateCourseByStudent, @PathVariable String courseId) throws CustomException {
        return new ResponseEntity<>(service.update(updateCourseByStudent, courseId), HttpStatus.OK);
    }
    @GetMapping("/get-all-students-selected-course/{courseId}")
    public ResponseEntity<?> getAllStudentByCourse(@PathVariable String courseId){
        return new ResponseEntity<>(service.getAllStudentsByCourse(courseId), HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-student/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long studentId) throws CustomException {
        return new ResponseEntity<>(service.deleteStudentById(studentId), HttpStatus.OK);
    }
}
