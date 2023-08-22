package com.courses.coursesservice.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AddCourseRequest {
    @NotBlank(message = "course title should not be empty")
    @Size(min = 4, message = "course title should be four or more than four characters")
    private String courseTitle;

    private String courseDescription;

    @NotBlank(message = "course code should not be blank")
    private String courseCode;

    @NotBlank(message = "learning objectives must not be  blank")
    private String learningObjectives;

    private String prerequisites;
}
