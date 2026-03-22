package com.hrd.my_batis.service;

import com.hrd.my_batis.model.Instructor;
import com.hrd.my_batis.repository.InstructorMapper;
import com.hrd.my_batis.request.InstructorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorMapper instructorMapper;

    public List<Instructor> getAllInstructors(int page, int size) {
        int offset = (page - 1) * size;
        return instructorMapper.getAllInstructors(offset, size);
    }

    public Instructor getInstructorById(Long instructorId) {
        Instructor instructor = instructorMapper.getInstructorById(instructorId);
        if (instructor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Instructor with ID " + instructorId + " not found");
        }
        return instructor;
    }

    public Instructor createInstructor(InstructorRequest request) {
        instructorMapper.createInstructor(request);
        Long id = instructorMapper.getLastInsertedId();
        return instructorMapper.getInstructorById(id);
    }

    public Instructor updateInstructor(Long instructorId, InstructorRequest request) {
        getInstructorById(instructorId);
        instructorMapper.updateInstructor(instructorId, request);
        return instructorMapper.getInstructorById(instructorId);
    }

    public void deleteInstructor(Long instructorId) {
        getInstructorById(instructorId);
        instructorMapper.deleteInstructor(instructorId);
    }
}
