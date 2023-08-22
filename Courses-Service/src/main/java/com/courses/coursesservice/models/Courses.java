package com.courses.coursesservice.models;

import com.courses.coursesservice.Audit.AuditEntity;
import com.courses.coursesservice.dtos.requests.AddCourseRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name= "courses", uniqueConstraints = {@UniqueConstraint(columnNames = {"course_code"})})
public class Courses extends AuditEntity {
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


    public Courses (AddCourseRequest request){
        this.courseTitle = request.getCourseTitle();
        this.courseDescription = request.getCourseDescription();
        this.courseCode = request.getCourseCode();
        this.learningObjectives = request.getLearningObjectives();
        this.prerequisites = request.getPrerequisites();
    }


    public static Courses getCoursesInstance(AddCourseRequest request){
        return new Courses(request);
    }
}
