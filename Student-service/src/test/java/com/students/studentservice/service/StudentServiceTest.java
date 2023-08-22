package com.students.studentservice.service;

import com.students.studentservice.config.JwtUtils;
import com.students.studentservice.config.SecurityUtils;
import com.students.studentservice.dto.requests.StudentRegistrationRequest;
import com.students.studentservice.dto.requests.UpdateCourseByStudent;
import com.students.studentservice.dto.response.ApiResponse;
import com.students.studentservice.exceptions.CustomException;
import com.students.studentservice.models.Courses;
import com.students.studentservice.models.Student;
import com.students.studentservice.repository.CourseRepository;
import com.students.studentservice.repository.StudentCourseRepository;
import com.students.studentservice.repository.StudentRepository;
import com.students.studentservice.service.serviceImpl.StudentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private  StudentRepository studentRepository;
    @Mock
    private JwtUtils utils;
    @Mock
    private  SecurityUtils securityUtils;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private StudentCourseRepository studentCourseRepository;
    @Mock
    private  CourseRepository courseRepository;


    @Test
    @DisplayName("should save the student object to the db")
    void save() throws CustomException {
        StudentRegistrationRequest request = new StudentRegistrationRequest();
        request.setAddress("1 avenue London");
        request.setEmailAddress("johndoe@email.com");
        request.setFullArabicName(null);
        request.setTelephoneNumber("+445556868696");
        request.setFullEnglishName("John Doe");


        Student student = new Student();
        student.setAddress("1 avenue London");
        student.setEmailAddress("johndoe@email.com");
        student.setId(1L);
        student.setFullArabicName(null);
        student.setTelephoneNumber("+445556868696");
        student.setFullEnglishName("John Doe");

        String token = "23458589590500599584885948_11";
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(utils.generateToken(any(UserDetails.class))).thenReturn(token);
        ApiResponse<?> newStudent = studentService.createStudent(request);

        assertThat(newStudent).isNotNull();
        assertThat(newStudent.isStatus()).isEqualTo(true);
    }

    @Test
    void updateCourseForStudent() throws CustomException {
        UpdateCourseByStudent courseByStudent = new UpdateCourseByStudent();
        courseByStudent.setCourseCode("PHY101");
        courseByStudent.setCourseTitle("physics");
        courseByStudent.setPrerequisites("none");
        courseByStudent.setCourseDescription("none");
        courseByStudent.setLearningObjectives("none");

        Courses course = new Courses();
        course.setPrerequisites("none");
        course.setCourseId("C001");
        course.setCourseTitle("physics");
        course.setCourseDescription("for 100level students");
        course.setCourseCode("PHY101");
        course.setLearningObjectives("All will be present");


        course.setCourseTitle("catechesis of technology");
        course.setCourseCode("CT101");

        when(courseRepository.findCoursesByCourseId("C001")).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(course);

     ApiResponse<?> updatedCourse =  studentService.update(courseByStudent, "C001");
        assertThat(updatedCourse).isNotNull();
        assertThat(updatedCourse.getMessage()).isEqualTo("success");
    }
}
