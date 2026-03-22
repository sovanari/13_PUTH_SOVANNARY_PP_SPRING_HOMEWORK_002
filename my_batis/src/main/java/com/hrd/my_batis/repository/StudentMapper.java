package com.hrd.my_batis.repository;

import com.hrd.my_batis.model.Student;
import com.hrd.my_batis.request.StudentRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> getAllStudents(@Param("offset") int offset, @Param("limit") int limit);

    Student getStudentById(@Param("studentId") Long studentId);
    void createStudent(@Param("req") StudentRequest request);
    Long getLastInsertedId();
    void insertStudentCourse(@Param("studentId") Long studentId, @Param("courseId") Long courseId);
    void deleteStudentCourses(@Param("studentId") Long studentId);
    void updateStudent(@Param("studentId") Long studentId, @Param("req") StudentRequest request);
    void deleteStudent(@Param("studentId") Long studentId);
}
