package com.hrd.my_batis.repository;

import com.hrd.my_batis.model.Instructor;
import com.hrd.my_batis.request.InstructorRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorMapper {

    @Results(id = "InstructorResultMap", value = {
            @Result(property = "instructorId",   column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "email",          column = "email")
    })
    @Select("""
            SELECT instructor_id, instructor_name, email
            FROM instructors
            LIMIT #{limit} OFFSET #{offset}
            """)
    List<Instructor> getAllInstructors(@Param("offset") int offset, @Param("limit") int limit);

    @ResultMap("InstructorResultMap")
    @Select("""
            SELECT instructor_id, instructor_name, email
            FROM instructors
            WHERE instructor_id = #{instructorId}
            """)
    Instructor getInstructorById(@Param("instructorId") Long instructorId);

    @Insert("""
            INSERT INTO instructors (instructor_name, email)
            VALUES (#{req.instructorName}, #{req.email})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "req.instructorId", keyColumn = "instructor_id")
    void createInstructor(@Param("req") InstructorRequest request);

    @Update("""
            UPDATE instructors
            SET instructor_name = #{req.instructorName},
                email           = #{req.email}
            WHERE instructor_id = #{instructorId}
            """)
    void updateInstructor(@Param("instructorId") Long instructorId, @Param("req") InstructorRequest request);

    @Delete("""
            DELETE FROM instructors
            WHERE instructor_id = #{instructorId}
            """)
    void deleteInstructor(@Param("instructorId") Long instructorId);
}
