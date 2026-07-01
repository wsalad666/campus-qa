package com.campusqa.mapper;
import com.campusqa.entity.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AnswerMapper {
    @Insert("INSERT INTO answer(question_id,user_id,content) VALUES(#{questionId},#{userId},#{content})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Answer a);

    @Select("SELECT a.*,u.username FROM answer a LEFT JOIN user u ON a.user_id=u.id WHERE a.question_id=#{questionId} ORDER BY a.is_accepted DESC, a.created_at ASC")
    List<Answer> findByQuestionId(Long questionId);

    @Update("UPDATE answer SET is_accepted=1 WHERE id=#{id}")
    void accept(Long id);

    @Select("SELECT * FROM answer WHERE id=#{id}")
    Answer findById(Long id);

    @Update("UPDATE answer SET like_count=like_count+1 WHERE id=#{id}")
    void incrLikeCount(Long id);
}
