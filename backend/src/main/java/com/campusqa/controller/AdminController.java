package com.campusqa.controller;

import com.campusqa.common.*;
import com.campusqa.entity.*;
import com.campusqa.service.AdminService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@Api(tags = "后台管理")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService as) { adminService = as; }

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result<Map<String,Object>> login(@RequestParam String username, @RequestParam String password) {
        return adminService.login(username, password);
    }

    @PutMapping("/questions/{id}/violation")
    @ApiOperation("违规问答下架")
    public Result<?> violation(@PathVariable Long id) {
        return adminService.violationQuestion(id);
    }

    @PostMapping("/courses")
    @ApiOperation("新增课程分类")
    public Result<?> addCourse(@RequestBody Course course) {
        return adminService.addCourse(course);
    }

    @PutMapping("/courses/{id}")
    @ApiOperation("修改课程分类")
    public Result<?> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return adminService.updateCourse(id, course);
    }

    @DeleteMapping("/courses/{id}")
    @ApiOperation("删除课程分类")
    public Result<?> deleteCourse(@PathVariable Long id) {
        return adminService.deleteCourse(id);
    }

    @GetMapping("/courses")
    @ApiOperation("课程列表")
    public Result<?> listCourses() {
        return adminService.listCourses();
    }

    @GetMapping("/stats/home")
    @ApiOperation("首页统计数据")
    public Result<Map<String,Object>> homeStats() {
        return adminService.homeStats();
    }

    @GetMapping("/stats/active-users")
    @ApiOperation("活跃用户统计")
    public Result<?> activeUsers() {
        return adminService.activeUsers();
    }
}
