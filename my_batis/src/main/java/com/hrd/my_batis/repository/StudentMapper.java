package com.hrd.my_batis.repository;

import com.hrd.my_batis.model.Student;
import com.hrd.my_batis.request.StudentRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Results(id = "StudentResultMap", value = {
            @Result(property = "studentId",   column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "email",       column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courses",     column = "student_id",
                    many = @Many(select = "com.hrd.my_batis.repository.StudentMapper.findCoursesByStudentId"))
    })
    @Select("""
            SELECT DISTINCT s.student_id,
                   s.student_name,
                   s.email,
                   s.phone_number
            FROM students s
            ORDER BY s.student_id
            LIMIT #{limit} OFFSET #{offset}
            """)
    List<Student> getAllStudents(@Param("offset") int offset, @Param("limit") int limit);

    @ResultMap("StudentResultMap")
    @Select("""
            SELECT s.student_id,
                   s.student_name,
                   s.email,
                   s.phone_number
            FROM students s
            WHERE s.student_id = #{studentId}
            """)
    Student getStudentById(@Param("studentId") Long studentId);

    @Results(id = "CourseWithInstructorMap", value = {
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
            LEFT JOIN student_course sc ON c.course_id = sc.course_id
            LEFT JOIN instructors i    ON c.instructor_id = i.instructor_id
            WHERE sc.student_id = #{studentId}
            """)
    List<com.hrd.my_batis.model.Course> findCoursesByStudentId(@Param("studentId") Long studentId);

    @Insert("""
            INSERT INTO students (student_name, email, phone_number)
            VALUES (#{req.studentName}, #{req.email}, #{req.phoneNumber})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "req.studentId", keyColumn = "student_id")
    void createStudent(@Param("req") StudentRequest request);

    @Insert("""
            INSERT INTO student_course (student_id, course_id)
            VALUES (#{studentId}, #{courseId})
            """)
    void insertStudentCourse(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

    @Delete("""
            DELETE FROM student_course
            WHERE student_id = #{studentId}
            """)
    void deleteStudentCourses(@Param("studentId") Long studentId);

    @Update("""
            UPDATE students
            SET student_name = #{req.studentName},
                email        = #{req.email},
                phone_number = #{req.phoneNumber}
            WHERE student_id = #{studentId}
            """)
    void updateStudent(@Param("studentId") Long studentId, @Param("req") StudentRequest request);

    @Delete("""
            DELETE FROM students
            WHERE student_id = #{studentId}
            """)
    void deleteStudent(@Param("studentId") Long studentId);
}
