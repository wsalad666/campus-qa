package com.campusqa.mapper;
import com.campusqa.entity.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface FavoriteMapper {
    @Insert("INSERT INTO favorite(user_id,question_id,resource_id) VALUES(#{userId},#{questionId},#{resourceId})")
    void insert(Favorite favorite);

    @Delete("DELETE FROM favorite WHERE id=#{id}")
    void delete(Long id);

    @Select("SELECT f.*,q.title as questionTitle FROM favorite f LEFT JOIN question q ON f.question_id=q.id WHERE f.user_id=#{userId}")
    List<Favorite> findByUserId(Long userId);

    @Select("SELECT EXISTS(SELECT 1 FROM favorite WHERE user_id=#{userId} AND question_id=#{questionId})")
    boolean existsQuestion(@Param("userId") Long userId, @Param("questionId") Long questionId);
}
