package com.courses.coursesservice.dtos.responses;

import com.courses.coursesservice.models.Courses;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CourseRegisterResponse {
    private String courseTitle;
    private Long courseId;

    public CourseRegisterResponse(Courses course){
        this.courseTitle = course.getCourseTitle();
        this.courseId = course.getId();
    }
}
