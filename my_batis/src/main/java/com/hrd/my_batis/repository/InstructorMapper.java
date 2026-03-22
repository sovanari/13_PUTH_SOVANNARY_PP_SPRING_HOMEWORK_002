package com.hrd.my_batis.repository;

import com.hrd.my_batis.model.Instructor;
import com.hrd.my_batis.request.InstructorRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InstructorMapper {

    List<Instructor> getAllInstructors(@Param("offset") int offset, @Param("limit") int limit);

    Instructor getInstructorById(@Param("instructorId") Long instructorId);
    void createInstructor(@Param("req") InstructorRequest request);
    Long getLastInsertedId();
    void updateInstructor(@Param("instructorId") Long instructorId, @Param("req") InstructorRequest request);
    void deleteInstructor(@Param("instructorId") Long instructorId);
}
