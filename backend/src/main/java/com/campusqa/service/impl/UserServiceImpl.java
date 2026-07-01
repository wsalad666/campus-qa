package com.campusqa.service.impl;

import com.campusqa.common.*;
import com.campusqa.entity.*;
import com.campusqa.mapper.*;
import com.campusqa.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final FollowMapper followMapper;
    private final FavoriteMapper favoriteMapper;
    private final QuestionMapper questionMapper;
    private final ResourceMapper resourceMapper;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserMapper um, FollowMapper fm, FavoriteMapper fam, QuestionMapper qm, ResourceMapper rm, JwtUtil j, BCryptPasswordEncoder e) {
        userMapper=um; followMapper=fm; favoriteMapper=fam; questionMapper=qm; resourceMapper=rm; jwtUtil=j; encoder=e;
    }

    public Result<?> register(User user) {
        if (userMapper.findByStudentId(user.getStudentId()) != null)
            return Result.error(400, "学号已注册");
        user.setPassword(encoder.encode(user.getPassword()));
        userMapper.insert(user);
        return Result.success();
    }

    public Result<Map<String,Object>> login(String studentId, String password) {
        User user = userMapper.findByStudentId(studentId);
        if (user == null || !encoder.matches(password, user.getPassword()))
            return Result.error(401, "学号或密码错误");
        String token = jwtUtil.generateToken(user.getId(), "user");
        Map<String,Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", user);
        return Result.success(map);
    }

    public Result<User> getProfile(Long userId) {
        User user = userMapper.findById(userId);
        if (user == null) return Result.error(404, "用户不存在");
        user.setPassword(null);
        Map<String,Object> extra = new HashMap<>();
        extra.put("followingCount", followMapper.countFollowing(userId));
        extra.put("followerCount", followMapper.countFollowers(userId));
        return Result.success(user);
    }

    public Result<?> updateProfile(Long userId, User u) {
        u.setId(userId);
        userMapper.update(u);
        return Result.success();
    }

    public Result<?> follow(Long followerId, Long followingId) {
        if (followerId.equals(followingId)) return Result.error(400, "不能关注自己");
        Follow f = new Follow(); f.setFollowerId(followerId); f.setFollowingId(followingId);
        followMapper.insert(f);
        return Result.success();
    }

    public Result<?> unfollow(Long followerId, Long followingId) {
        followMapper.delete(followerId, followingId);
        return Result.success();
    }

    public Result<?> getMyQuestions(Long userId) {
        return Result.success(questionMapper.findByUserId(userId));
    }

    public Result<?> getMyResources(Long userId) {
        return Result.success(null);
    }
}
