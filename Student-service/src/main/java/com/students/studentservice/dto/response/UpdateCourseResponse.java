package com.students.studentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCourseResponse {
    private boolean status;
    private String message;
    private CourseResp data;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class CourseResp {
        private Long id;

        private String courseId;

        private String courseTitle;

        private String courseDescription;

        private String courseCode;

        private String learningObjectives;

        private String prerequisites;
    }
}
