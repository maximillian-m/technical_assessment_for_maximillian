package com.students.studentservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Student student;

    private String courseId;
}
