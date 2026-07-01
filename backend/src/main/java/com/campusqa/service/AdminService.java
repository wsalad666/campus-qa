package com.campusqa.service;
import com.campusqa.entity.*;
import com.campusqa.common.Result;
import java.util.Map;

public interface AdminService {
    Result<Map<String,Object>> login(String username, String password);
    Result<?> listQuestions(Integer status);
    Result<?> violationQuestion(Long questionId);
    Result<?> addCourse(Course course);
    Result<?> updateCourse(Long id, Course course);
    Result<?> deleteCourse(Long id);
    Result<?> listCourses();
    Result<Map<String,Object>> homeStats();
    Result<?> activeUsers();
}
