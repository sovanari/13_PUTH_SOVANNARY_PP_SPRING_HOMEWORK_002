package com.hrd.my_batis.service;

import com.hrd.my_batis.model.Student;
import com.hrd.my_batis.repository.StudentMapper;
import com.hrd.my_batis.request.StudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentMapper studentMapper;

    public List<Student> getAllStudents(int page, int size) {
        int offset = (page - 1) * size;
        return studentMapper.getAllStudents(offset, size);
    }

    public Student getStudentById(Long studentId) {
        Student student = studentMapper.getStudentById(studentId);
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Student with ID " + studentId + " not found");
        }
        return student;
    }

    public Student createStudent(StudentRequest request) {
        studentMapper.createStudent(request);
        Long id = studentMapper.getLastInsertedId();
        // Insert course enrollments
        if (request.getCourseId() != null) {
            for (Long courseId : request.getCourseId()) {
                studentMapper.insertStudentCourse(id, courseId);
            }
        }
        return studentMapper.getStudentById(id);
    }

    public Student updateStudent(Long studentId, StudentRequest request) {
        getStudentById(studentId);
        studentMapper.updateStudent(studentId, request);
        studentMapper.deleteStudentCourses(studentId);
        if (request.getCourseId() != null) {
            for (Long courseId : request.getCourseId()) {
                studentMapper.insertStudentCourse(studentId, courseId);
            }
        }
        return studentMapper.getStudentById(studentId);
    }

    public void deleteStudent(Long studentId) {
        getStudentById(studentId);
        studentMapper.deleteStudentCourses(studentId);
        studentMapper.deleteStudent(studentId);
    }
}
