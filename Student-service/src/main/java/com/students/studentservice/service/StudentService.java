package com.students.studentservice.service;

import com.students.studentservice.dto.requests.StudentRegistrationRequest;
import com.students.studentservice.dto.requests.UpdateCourseByStudent;
import com.students.studentservice.dto.response.ApiResponse;
import com.students.studentservice.dto.response.CourseResponseList;
import com.students.studentservice.exceptions.CustomException;
import org.springframework.http.HttpStatusCode;

public interface StudentService {
    ApiResponse<?> createStudent(StudentRegistrationRequest request) throws CustomException;

    CourseResponseList getListOfcourse();

    ApiResponse<?> allocateCourse(String courseId) throws CustomException;

    ApiResponse<?> update(UpdateCourseByStudent updateCourseByStudent, String courseId) throws CustomException;

    ApiResponse<?> getAllStudentsByCourse(String courseId);

    ApiResponse<?> deleteStudentById(Long studentId) throws CustomException;
}
