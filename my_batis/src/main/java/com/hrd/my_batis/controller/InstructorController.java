package com.hrd.my_batis.controller;

import com.hrd.my_batis.model.ApiResponse;
import com.hrd.my_batis.model.Instructor;
import com.hrd.my_batis.request.InstructorRequest;
import com.hrd.my_batis.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(
            @RequestBody InstructorRequest request) {
        Instructor instructor = instructorService.createInstructor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "201 CREATED", "Instructor created successfully",
                        instructor, LocalDateTime.now())
        );
    }

    @GetMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(
            @PathVariable("instructor-id") Long id) {
        Instructor instructor = instructorService.getInstructorById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "Instructor retrieved successfully",
                        instructor, LocalDateTime.now())
        );
    }

    @PutMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(
            @PathVariable("instructor-id") Long id,
            @RequestBody InstructorRequest request) {
        Instructor instructor = instructorService.updateInstructor(id, request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "Instructor updated successfully",
                        instructor, LocalDateTime.now())
        );
    }

    @DeleteMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Void>> deleteInstructor(
            @PathVariable("instructor-id") Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "Instructor deleted successfully",
                        null, LocalDateTime.now())
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Instructor> instructors = instructorService.getAllInstructors(page, size);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "200 OK", "All instructors retrieved successfully",
                        instructors, LocalDateTime.now())
        );
    }
}
