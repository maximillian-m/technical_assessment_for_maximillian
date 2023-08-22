package com.students.studentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseResponseList {
    private boolean status;
    private String message;
    private List<CourseResponse> data;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class CourseResponse {
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
