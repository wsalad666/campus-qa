package com.campusqa.service;
import com.campusqa.entity.*;
import com.campusqa.common.Result;

public interface FavoriteService {
    Result<?> addFavorite(Favorite favorite);
    Result<?> removeFavorite(Long id);
    Result<?> getMyFavorites(Long userId);
}
