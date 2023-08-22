package com.courses.coursesservice.dtos.responses;

import com.courses.coursesservice.models.Courses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public  class CourseResponse {
        private String courseId;
        private String courseTitle;
        private String courseDescription;
        private String courseCode;
        private String learningObjectives;
        private String prerequisites;
        private Date createdAt;
        private Date updatedAt;

        public CourseResponse(Courses courses){
            this.courseId = courses.getCourseId();
            this.courseTitle = courses.getCourseTitle();
            this.courseCode = courses.getCourseCode();
            this.courseDescription = courses.getCourseDescription();
            this.learningObjectives = courses.getLearningObjectives();
            this.prerequisites = courses.getPrerequisites();
            this.createdAt = courses.getCreatedAt();
            this.updatedAt = courses.getUpdatedAt();
        }
    }
