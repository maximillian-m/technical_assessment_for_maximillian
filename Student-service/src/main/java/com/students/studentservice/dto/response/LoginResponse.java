package com.students.studentservice.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LoginResponse {
    private String token;
    private String userName;
}
