package com.campusqa.mapper;
import com.campusqa.entity.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO comment(answer_id,user_id,content) VALUES(#{answerId},#{userId},#{content})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Comment c);

    @Select("SELECT c.*,u.username FROM comment c LEFT JOIN user u ON c.user_id=u.id WHERE c.answer_id=#{answerId} ORDER BY c.created_at ASC")
    List<Comment> findByAnswerId(Long answerId);
}
