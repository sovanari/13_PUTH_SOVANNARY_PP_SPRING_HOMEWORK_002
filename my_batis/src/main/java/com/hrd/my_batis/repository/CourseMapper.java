package com.hrd.my_batis.repository;

import com.hrd.my_batis.model.Course;
import com.hrd.my_batis.request.CourseRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<Course> getAllCourses(@Param("offset") int offset, @Param("limit") int limit);
    Course getCourseById(@Param("courseId") Long courseId);
    void createCourse(@Param("req") CourseRequest request);
    Long getLastInsertedId();
    void updateCourse(@Param("courseId") Long courseId, @Param("req") CourseRequest request);
    void deleteCourse(@Param("courseId") Long courseId);
}
