package com.courses.coursesservice.controller;

import com.courses.coursesservice.dtos.requests.AddCourseRequest;
import com.courses.coursesservice.exceptions.CustomException;
import com.courses.coursesservice.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@Valid @RequestBody AddCourseRequest request) throws CustomException {
        return new ResponseEntity<>(courseService.addCourses(request), HttpStatusCode.valueOf(201));
    }
    @GetMapping("/all-course")
    public ResponseEntity<?> getAllCourse() throws CustomException {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.FOUND);
    }
    @GetMapping("/get-course/{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable String courseId) throws CustomException {
        return new ResponseEntity<>(courseService.getCourses(courseId), HttpStatus.FOUND);
    }
    @PutMapping("/update-course/{courseId}")
    public ResponseEntity<?> updateCourse(@RequestBody AddCourseRequest request, @PathVariable String courseId) throws CustomException {
        return new ResponseEntity<>(courseService.updateCourse(request, courseId), HttpStatus.OK);
    }
    @DeleteMapping("/delete-course/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable String courseId) throws CustomException {
        return new ResponseEntity<>(courseService.deleteCourse(courseId), HttpStatus.OK);
    }

}