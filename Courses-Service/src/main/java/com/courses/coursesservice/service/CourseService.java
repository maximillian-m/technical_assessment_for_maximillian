package com.courses.coursesservice.service;

import com.courses.coursesservice.dtos.requests.AddCourseRequest;
import com.courses.coursesservice.dtos.responses.ApiResponse;
import com.courses.coursesservice.dtos.responses.CourseRegisterResponse;
import com.courses.coursesservice.dtos.responses.CourseResponse;
import com.courses.coursesservice.exceptions.CustomException;

import java.util.List;

public interface CourseService {
    ApiResponse<CourseRegisterResponse> addCourses(AddCourseRequest request) throws CustomException;

    ApiResponse<List<CourseResponse>> getAllCourses() throws CustomException;

    ApiResponse<?> updateCourse(AddCourseRequest request, String courseId) throws CustomException;

    ApiResponse<?> deleteCourse(String courseId) throws CustomException;

    ApiResponse<?> getCourses(String courseId) throws CustomException;
}
