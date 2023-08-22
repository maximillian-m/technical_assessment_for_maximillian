package com.students.studentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseResponse {
    private boolean status;

    private String message;

    private Res data;
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Res {
        private String courseId;
        private String courseTitle;
        private String courseDescription;
        private String courseCode;
        private String learningObjectives;
        private String prerequisites;
        private Date createdAt;
        private Date updatedAt;
    }
}
