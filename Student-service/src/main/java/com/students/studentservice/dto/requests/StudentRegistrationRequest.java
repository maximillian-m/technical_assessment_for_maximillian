package com.students.studentservice.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRegistrationRequest {

    @NotBlank(message ="English name should not be empty")
    private String fullEnglishName;
    @NotBlank(message="Arabic name should not be empty")
    private String fullArabicName;
    @NotBlank(message ="email address should not be empty")
    private String emailAddress;
    private String telephoneNumber;
    private String address;
}
