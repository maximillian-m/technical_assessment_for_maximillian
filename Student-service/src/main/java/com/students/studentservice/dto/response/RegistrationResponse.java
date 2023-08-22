package com.students.studentservice.dto.response;

import com.students.studentservice.models.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationResponse {
    private String userName;
    private Long studentId;
    private String token;


    public RegistrationResponse(Student student){
        this.userName = student.getFullEnglishName();
        this.studentId = student.getId();
    }
}
