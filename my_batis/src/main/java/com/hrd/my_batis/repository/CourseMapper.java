package com.hrd.my_batis.repository;

import com.hrd.my_batis.model.Course;
import com.hrd.my_batis.request.CourseRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Results(id = "CourseResultMap", value = {
            @Result(property = "courseId",    column = "course_id"),
            @Result(property = "courseName",  column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor.instructorId",   column = "instructor_id"),
            @Result(property = "instructor.instructorName", column = "instructor_name"),
            @Result(property = "instructor.email",          column = "instructor_email")
    })
    @Select("""
            SELECT c.course_id,
                   c.course_name,
                   c.description,
                   i.instructor_id,
                   i.instructor_name,
                   i.email AS instructor_email
            FROM courses c
            LEFT JOIN instructors i ON c.instructor_id = i.instructor_id
            LIMIT #{limit} OFFSET #{offset}
            """)
    List<Course> getAllCourses(@Param("offset") int offset, @Param("limit") int limit);

    @ResultMap("CourseResultMap")
    @Select("""
            SELECT c.course_id,
                   c.course_name,
                   c.description,
                   i.instructor_id,
                   i.instructor_name,
                   i.email AS instructor_email
            FROM courses c
            LEFT JOIN instructors i ON c.instructor_id = i.instructor_id
            WHERE c.course_id = #{courseId}
            """)
    Course getCourseById(@Param("courseId") Long courseId);

    @Insert("""
            INSERT INTO courses (course_name, description, instructor_id)
            VALUES (#{req.courseName}, #{req.description}, #{req.instructorId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "req.courseId", keyColumn = "course_id")
    void createCourse(@Param("req") CourseRequest request);

    @Update("""
            UPDATE courses
            SET course_name   = #{req.courseName},
                description   = #{req.description},
                instructor_id = #{req.instructorId}
            WHERE course_id = #{courseId}
            """)
    void updateCourse(@Param("courseId") Long courseId, @Param("req") CourseRequest request);

    @Delete("""
            DELETE FROM courses
            WHERE course_id = #{courseId}
            """)
    void deleteCourse(@Param("courseId") Long courseId);
}
