package com.hrd.my_batis.controller;

import com.hrd.my_batis.model.ApiResponse;
import com.hrd.my_batis.model.Course;
import com.hrd.my_batis.request.CourseRequest;
import com.hrd.my_batis.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(
            @RequestBody CourseRequest request) {
        Course course = courseService.createCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "201 CREATED", "Course created successfully",
                        course, LocalDateTime.now())
        );
    }

    @GetMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(
            @PathVariable("course-id") Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "Course retrieved successfully",
                        course, LocalDateTime.now())
        );
    }

    @PutMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(
            @PathVariable("course-id") Long id,
            @RequestBody CourseRequest request) {
        Course course = courseService.updateCourse(id, request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "Course updated successfully",
                        course, LocalDateTime.now())
        );
    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(
            @PathVariable("course-id") Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "Course deleted successfully",
                        null, LocalDateTime.now())
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Course> courses = courseService.getAllCourses(page, size);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "All courses retrieved successfully",
                        courses, LocalDateTime.now())
        );
    }
}
