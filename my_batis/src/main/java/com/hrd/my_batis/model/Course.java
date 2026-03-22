package com.hrd.my_batis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Long courseId;
    private String courseName;
    private String description;
    private Instructor instructor;
}
