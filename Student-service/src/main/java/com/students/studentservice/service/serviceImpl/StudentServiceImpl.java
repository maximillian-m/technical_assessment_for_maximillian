package com.students.studentservice.service.serviceImpl;

import com.students.studentservice.config.JwtUtils;
import com.students.studentservice.config.SecurityUtils;
import com.students.studentservice.dto.requests.StudentRegistrationRequest;
import com.students.studentservice.dto.requests.UpdateCourseByStudent;
import com.students.studentservice.dto.response.*;
import com.students.studentservice.exceptions.CustomException;
import com.students.studentservice.models.Courses;
import com.students.studentservice.models.Student;
import com.students.studentservice.models.StudentCourse;
import com.students.studentservice.repository.CourseRepository;
import com.students.studentservice.repository.StudentCourseRepository;
import com.students.studentservice.repository.StudentRepository;
import com.students.studentservice.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final JwtUtils utils;
    private final SecurityUtils securityUtils;
    private final RestTemplate restTemplate;
    private final StudentCourseRepository studentCourseRepository;
    private final CourseRepository courseRepository;
    @Override
    public ApiResponse<?> createStudent(StudentRegistrationRequest request) throws CustomException {
        Student student = new Student(request);
        Student saved = null;
        try {
            saved = studentRepository.save(student);
        }catch (Exception e){
            throw new CustomException("student with email already exists", HttpStatus.CONFLICT);
        }
        String token = utils.generateToken(student);
        RegistrationResponse response = new RegistrationResponse(saved);
        response.setToken(token);
        return new ApiResponse<>(true, "success", response);
    }

    @Override
    public CourseResponseList getListOfcourse() {
        return restTemplate.exchange("http://localhost:8080/api/v1/courses/all-course",
                HttpMethod.GET, null, CourseResponseList.class).getBody();
    }

    @Override
    public ApiResponse<?> allocateCourse(String courseId) throws CustomException {
        Student student = getStudent();
        CourseResponse response;
        try {
         response = restTemplate.exchange("http://localhost:8080/api/v1/courses/get-course/"+courseId,
                HttpMethod.GET, null, CourseResponse.class).getBody();
    }catch (HttpClientErrorException e){
        throw new CustomException("Error while parsing data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(response != null) {
            Courses courses = new Courses(response.getData());
            courseRepository.save(courses);

            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setStudent(student);
            studentCourse.setCourseId(courses.getCourseId());

            StudentCourse saved = studentCourseRepository.save(studentCourse);
            return new ApiResponse<>(true, "success", saved);
        }
       throw new CustomException("error occurred while parsing", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @Transactional
    @Modifying
    public ApiResponse<?> update(UpdateCourseByStudent updateCourseByStudent, String courseId) throws CustomException {
       Courses course = courseRepository.findCoursesByCourseId((courseId))
               .orElseThrow(() -> new CustomException("course not found", HttpStatus.NOT_FOUND));
       Courses updated = courseDtoToCourse(updateCourseByStudent, course);
        return new ApiResponse<>(true, "success", courseRepository.save(updated));
    }

    @Override
    public ApiResponse<?> getAllStudentsByCourse(String courseId) {
        List<Student> retrievedStudents = studentCourseRepository.findStudentByCourseId(courseId)
                .orElse(new ArrayList<>());
        List<StudentResponse> responses = retrievedStudents.stream().map(StudentResponse::new).toList();
        return new ApiResponse<>(true, "success", responses);
    }

    @Override
    public ApiResponse<?> deleteStudentById(Long studentId) throws CustomException {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
            List<StudentCourse> studentCourses = studentCourseRepository.findByStudentId(studentId);
        try{
            studentCourseRepository.deleteAll(studentCourses);
            studentRepository.deleteById(studentId);
            return new ApiResponse<>(true, "success", "student deleted");
        } catch (Exception e){
            throw  new CustomException("Error deleting student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private Student getStudent() throws CustomException {
        String email = securityUtils.getCurrentUserDetails().getUsername();
        return studentRepository.findStudentByEmailAddress(email)
                .orElseThrow(() -> new CustomException("student does not exist", HttpStatus.NOT_FOUND));
    }

    private Courses courseDtoToCourse(UpdateCourseByStudent request, Courses courses){
        courses.setCourseCode(request.getCourseCode());
        courses.setCourseDescription(request.getCourseDescription());
        courses.setCourseTitle(request.getCourseTitle());
        courses.setLearningObjectives(request.getLearningObjectives());
        courses.setPrerequisites(request.getPrerequisites());
        return courses;
    }
}
