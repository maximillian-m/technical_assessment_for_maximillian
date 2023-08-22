package com.courses.coursesservice.service.serviceImpl;

import com.courses.coursesservice.dtos.requests.AddCourseRequest;
import com.courses.coursesservice.dtos.responses.ApiResponse;
import com.courses.coursesservice.dtos.responses.CourseRegisterResponse;
import com.courses.coursesservice.dtos.responses.CourseResponse;
import com.courses.coursesservice.exceptions.CustomException;
import com.courses.coursesservice.models.Courses;
import com.courses.coursesservice.repository.CourseRepository;
import com.courses.coursesservice.service.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.courses.coursesservice.models.Courses.getCoursesInstance;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    @Override
    public ApiResponse<CourseRegisterResponse> addCourses(AddCourseRequest request) throws CustomException {
        String courseId = UUID.randomUUID().toString();
        Courses newCourses = getCoursesInstance(request);
        newCourses.setCourseId(courseId);
        CourseRegisterResponse savedCourses = null;
        try{
            savedCourses = new CourseRegisterResponse(courseRepository.save(newCourses));
        }catch (Exception e){
            throw new CustomException("course with course code "+ request.getCourseCode() +" already exists", HttpStatus.CONFLICT);
        }
        return new ApiResponse<>(true, "success", savedCourses);
    }

    @Override
    public ApiResponse<List<CourseResponse>> getAllCourses() throws CustomException {
       List<Courses> listOfCourses = courseRepository.findAll();
       if(listOfCourses.isEmpty()){
           throw new CustomException("no course found", HttpStatus.NOT_FOUND);
       }
       List<CourseResponse> allCoursesResponses = listOfCourses
               .stream().map(CourseResponse::new).toList();
       return new ApiResponse<>(true, "success", allCoursesResponses);
    }

    @Override
    @Transactional
    @Modifying
    public ApiResponse<?> updateCourse(AddCourseRequest request, String courseId) throws CustomException {
        Courses course = courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new CustomException("course not found", HttpStatus.NOT_FOUND));

        Courses updatedCourse = courseDtoToCourse(request, course);
        courseRepository.save(updatedCourse);
        return new ApiResponse<>(true, "success", updatedCourse);
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteCourse(String courseId) throws CustomException {
        Courses course = courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new CustomException("course not found", HttpStatus.NOT_FOUND));
        try {
            courseRepository.delete(course);
        }catch (Exception e){
            throw new CustomException("course deletion failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ApiResponse<>(true, "success", "deletion was successful");
    }

    @Override
    public ApiResponse<?> getCourses(String courseId) throws CustomException {
        Courses course = courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new CustomException("course not found", HttpStatus.NOT_FOUND));
        return new ApiResponse<>(true, "success", new CourseResponse(course));
    }

    private Courses courseDtoToCourse(AddCourseRequest request, Courses courses){
        courses.setCourseCode(request.getCourseCode());
        courses.setCourseDescription(request.getCourseDescription());
        courses.setCourseTitle(request.getCourseTitle());
        courses.setLearningObjectives(request.getLearningObjectives());
        courses.setPrerequisites(request.getPrerequisites());
        return courses;
    }
}
