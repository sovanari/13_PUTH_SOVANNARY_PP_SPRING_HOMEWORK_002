package com.hrd.my_batis.service;

import com.hrd.my_batis.model.Course;
import com.hrd.my_batis.repository.CourseMapper;
import com.hrd.my_batis.request.CourseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseMapper courseMapper;

    public List<Course> getAllCourses(int page, int size) {
        int offset = (page - 1) * size;
        return courseMapper.getAllCourses(offset, size);
    }

    public Course getCourseById(Long courseId) {
        Course course = courseMapper.getCourseById(courseId);
        if (course == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Course with ID " + courseId + " not found");
        }
        return course;
    }

    public Course createCourse(CourseRequest request) {
        courseMapper.createCourse(request);
        Long id = courseMapper.getLastInsertedId();
        return courseMapper.getCourseById(id);
    }

    public Course updateCourse(Long courseId, CourseRequest request) {
        getCourseById(courseId);
        courseMapper.updateCourse(courseId, request);
        return courseMapper.getCourseById(courseId);
    }

    public void deleteCourse(Long courseId) {
        getCourseById(courseId);
        courseMapper.deleteCourse(courseId);
    }
}
