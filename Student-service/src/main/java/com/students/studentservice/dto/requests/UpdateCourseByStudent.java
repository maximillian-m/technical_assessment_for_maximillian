package com.students.studentservice.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCourseByStudent {
    private String courseTitle;

    private String courseDescription;

    private String courseCode;

    private String learningObjectives;

    private String prerequisites;
}
