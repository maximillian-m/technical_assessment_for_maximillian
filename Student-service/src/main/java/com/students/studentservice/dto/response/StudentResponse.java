package com.students.studentservice.dto.response;

import com.students.studentservice.models.Student;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String fullEnglishName;
    private String fullArabicName;
    private String emailAddress;
    private String telephoneNumber;
    private String address;

    public StudentResponse(Student student){
        this.id = student.getId();
        this.address = student.getAddress();
        this.emailAddress = student.getEmailAddress();
        this.fullArabicName = student.getFullArabicName();
        this.fullEnglishName = student.getFullEnglishName();
        this.telephoneNumber = student.getTelephoneNumber();
    }
}
