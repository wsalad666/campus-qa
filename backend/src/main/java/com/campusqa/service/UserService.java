package com.campusqa.service;
import com.campusqa.entity.*;
import com.campusqa.common.Result;
import java.util.Map;

public interface UserService {
    Result<?> register(User user);
    Result<Map<String,Object>> login(String studentId, String password);
    Result<User> getProfile(Long userId);
    Result<?> updateProfile(Long userId, User user);
    Result<?> follow(Long followerId, Long followingId);
    Result<?> unfollow(Long followerId, Long followingId);
    Result<?> getMyQuestions(Long userId);
    Result<?> getMyResources(Long userId);
}
