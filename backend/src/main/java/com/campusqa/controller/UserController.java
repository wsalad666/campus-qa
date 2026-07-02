package com.campusqa.controller;

import com.campusqa.common.*;
import com.campusqa.entity.*;
import com.campusqa.service.UserService;
import com.campusqa.service.FavoriteService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户模块")
public class UserController {
    private final UserService userService;
    private final FavoriteService favoriteService;

    public UserController(UserService us, FavoriteService fs) {
        userService = us; favoriteService = fs;
    }

    @PostMapping("/register")
    @ApiOperation("注册（学号认证）")
    public Result<?> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    @ApiOperation("登录（返回JWT）")
    public Result<Map<String,Object>> login(@RequestParam String studentId, @RequestParam String password) {
        return userService.login(studentId, password);
    }

    @GetMapping("/profile")
    @ApiOperation("查看个人主页")
    public Result<User> profile(@RequestParam Long userId) {
        return userService.getProfile(userId);
    }

    @PutMapping("/profile")
    @ApiOperation("修改个人资料")
    public Result<?> updateProfile(@RequestParam Long userId, @RequestBody User user) {
        return userService.updateProfile(userId, user);
    }

    @PostMapping("/follow")
    @ApiOperation("关注好友")
    public Result<?> follow(@RequestParam Long followerId, @RequestParam Long followingId) {
        return userService.follow(followerId, followingId);
    }

    @DeleteMapping("/follow")
    @ApiOperation("取消关注")
    public Result<?> unfollow(@RequestParam Long followerId, @RequestParam Long followingId) {
        return userService.unfollow(followerId, followingId);
    }

    @GetMapping("/my-questions")
    @ApiOperation("我的问题")
    public Result<?> myQuestions(@RequestParam Long userId) {
        return userService.getMyQuestions(userId);
    }

    @PostMapping("/favorite")
    @ApiOperation("添加收藏")
    public Result<?> addFavorite(@RequestBody Favorite favorite) {
        return favoriteService.addFavorite(favorite);
    }

    @DeleteMapping("/favorite/{id}")
    @ApiOperation("取消收藏")
    public Result<?> removeFavorite(@PathVariable Long id) {
        return favoriteService.removeFavorite(id);
    }

    @GetMapping("/favorites")
    @ApiOperation("我的收藏列表")
    public Result<?> myFavorites(@RequestParam Long userId) {
        return favoriteService.getMyFavorites(userId);
    }
}
