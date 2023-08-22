package com.students.studentservice.models;

import com.students.studentservice.dto.response.CourseResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name= "courses", uniqueConstraints = {@UniqueConstraint(columnNames = {"course_code"})})
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseId;
    private String courseTitle;
    @Column(columnDefinition = "TEXT")
    private String courseDescription;
    @Column(name="course_code")
    private String courseCode;
    @Column(columnDefinition = "TEXT")
    private String learningObjectives;
    private String prerequisites;

    public Courses (CourseResponse.Res response){
        this.courseCode = response.getCourseCode();
        this.courseTitle = response.getCourseTitle();
        this.courseDescription = response.getCourseDescription();
        this.learningObjectives = response.getLearningObjectives();
        this.courseId =response.getCourseId();
        this.prerequisites = response.getPrerequisites();
    }
}
