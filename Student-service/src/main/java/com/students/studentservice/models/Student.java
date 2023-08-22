package com.students.studentservice.models;

import com.students.studentservice.dto.requests.StudentRegistrationRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name= "student", uniqueConstraints = {@UniqueConstraint(columnNames = {"email_address"})})
public class Student implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullEnglishName;
    private String fullArabicName;
    @Column(name="email_address")
    private String emailAddress;
    private String telephoneNumber;
    private String address;
public Student(StudentRegistrationRequest request){
    this.fullArabicName = request.getFullArabicName();
    this.fullEnglishName = request.getFullEnglishName();
    this.address = request.getAddress();
    this.emailAddress = request.getEmailAddress();
    this.telephoneNumber = request.getTelephoneNumber();
}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
