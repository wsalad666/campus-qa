package com.campusqa.mapper;
import com.campusqa.entity.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface QuestionLikeMapper {
    @Insert("INSERT INTO question_like(question_id,user_id) VALUES(#{questionId},#{userId})")
    void insert(QuestionLike like);

    @Delete("DELETE FROM question_like WHERE question_id=#{questionId} AND user_id=#{userId}")
    void delete(@Param("questionId") Long questionId, @Param("userId") Long userId);

    @Select("SELECT EXISTS(SELECT 1 FROM question_like WHERE question_id=#{questionId} AND user_id=#{userId})")
    boolean exists(@Param("questionId") Long questionId, @Param("userId") Long userId);
}
