package com.campusqa.service.impl;
import com.campusqa.common.*;
import com.campusqa.entity.*;
import com.campusqa.mapper.*;
import com.campusqa.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final CourseMapper courseMapper;
    private final QuestionMapper questionMapper;
    private final UserMapper userMapper;
    private final ResourceMapper resourceMapper;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;

    public AdminServiceImpl(AdminMapper am, CourseMapper cm, QuestionMapper qm, UserMapper um, ResourceMapper rm, JwtUtil j, BCryptPasswordEncoder e) {
        adminMapper=am; courseMapper=cm; questionMapper=qm; userMapper=um; resourceMapper=rm; jwtUtil=j; encoder=e;
    }

    public Result<Map<String,Object>> login(String username, String password) {
        Admin admin = adminMapper.findByUsername(username);
        if (admin == null || !encoder.matches(password, admin.getPassword()))
            return Result.error(401, "账号或密码错误");
        Map<String,Object> map = new HashMap<>();
        map.put("token", jwtUtil.generateToken(admin.getId(), "admin"));
        return Result.success(map);
    }

    public Result<?> listQuestions(Integer status) { return Result.success(null); }
    public Result<?> violationQuestion(Long questionId) {
        questionMapper.updateStatus(questionId, 0);
        return Result.success();
    }
    public Result<?> addCourse(Course c) { courseMapper.insert(c); return Result.success(); }
    public Result<?> updateCourse(Long id, Course c) { c.setId(id); courseMapper.update(c); return Result.success(); }
    public Result<?> deleteCourse(Long id) { courseMapper.delete(id); return Result.success(); }
    public Result<?> listCourses() { return Result.success(courseMapper.findAll()); }

    public Result<Map<String,Object>> homeStats() {
        Map<String,Object> stats = new HashMap<>();
        stats.put("questionCount", questionMapper.totalCount());
        stats.put("userCount", 0);
        stats.put("resourceCount", resourceMapper.totalCount());
        stats.put("todayQuestions", questionMapper.todayCount());
        return Result.success(stats);
    }

    public Result<?> activeUsers() { return Result.success(new ArrayList<>()); }
}
