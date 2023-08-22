package com.courses.coursesservice.repository;

import com.courses.coursesservice.models.Courses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @Test
    @DisplayName("it should return all saved courses and list with size of time ")
    void getAllCourses(){
        Courses course = new Courses();
        course.setPrerequisites("none");
        course.setCourseTitle("physics");
        course.setCourseDescription("for 100level students");
        course.setCourseCode("PHY101");
        course.setLearningObjectives("All will be present");
        Courses savedCourse =  courseRepository.save(course);

        Courses course2 = new Courses();
        course.setPrerequisites("none");
        course.setCourseTitle("chemistry");
        course.setCourseDescription("for 100level students");
        course.setCourseCode("CHM101");
        course.setLearningObjectives("All will be present");

        Courses savedCourse2 =  courseRepository.save(course2);

       List<Courses> list = courseRepository.findAll();

       assertNotNull(list);
       assertEquals(12,list.size());
    }

    @Test
    void getCourseById(){
        Courses course = new Courses();
        course.setCourseId("C0211");
        course.setPrerequisites("none");
        course.setCourseTitle("physics");
        course.setCourseDescription("for 100level students");
        course.setCourseCode("PHY101");
        course.setLearningObjectives("All will be present");
        Courses savedCourse =  courseRepository.save(course);

        Courses retrievedCourse = courseRepository.findByCourseId(course.getCourseId())
                .orElse(null);

        assertThat(retrievedCourse).isNotNull();
    }
}
