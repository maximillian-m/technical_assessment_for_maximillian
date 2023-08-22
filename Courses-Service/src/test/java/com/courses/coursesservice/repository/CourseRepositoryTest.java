package com.courses.coursesservice.repository;

import com.courses.coursesservice.models.Courses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    @DisplayName("it should save course to the database")
    void save(){
        Courses course = new Courses();
        course.setPrerequisites("none");
        course.setCourseTitle("physics");
        course.setCourseDescription("for 100level students");
        course.setCourseCode("PHY101");
        course.setLearningObjectives("All will be present");


       Courses savedCourse =  courseRepository.save(course);

       assertNotNull(savedCourse);
       assertThat(savedCourse.getId()).isNotEqualTo(null);

    }


}
