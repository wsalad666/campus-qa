package com.campusqa.service.impl;
import com.campusqa.common.*;
import com.campusqa.entity.*;
import com.campusqa.mapper.*;
import com.campusqa.service.FavoriteService;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteMapper favoriteMapper;
    public FavoriteServiceImpl(FavoriteMapper fm) { favoriteMapper=fm; }

    public Result<?> addFavorite(Favorite f) {
        if (f.getQuestionId()!=null && favoriteMapper.existsQuestion(f.getUserId(), f.getQuestionId()))
            return Result.error(400, "已收藏");
        favoriteMapper.insert(f);
        return Result.success();
    }

    public Result<?> removeFavorite(Long id) {
        favoriteMapper.delete(id);
        return Result.success();
    }

    public Result<?> getMyFavorites(Long userId) {
        return Result.success(favoriteMapper.findByUserId(userId));
    }
}
