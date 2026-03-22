package com.hrd.my_batis.controller;

import com.hrd.my_batis.model.ApiResponse;
import com.hrd.my_batis.model.Student;
import com.hrd.my_batis.request.StudentRequest;
import com.hrd.my_batis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(
            @RequestBody StudentRequest request) {
        Student student = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "201 CREATED", "Student created successfully",
                        student, LocalDateTime.now())
        );
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(
            @PathVariable("student-id") Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "Student retrieved successfully",
                        student, LocalDateTime.now())
        );
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(
            @PathVariable("student-id") Long id,
            @RequestBody StudentRequest request) {
        Student student = studentService.updateStudent(id, request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "Student updated successfully",
                        student, LocalDateTime.now())
        );
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(
            @PathVariable("student-id") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "Student deleted successfully",
                        null, LocalDateTime.now())
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Student> students = studentService.getAllStudents(page, size);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "All students retrieved successfully",
                        students, LocalDateTime.now())
        );
    }
}
