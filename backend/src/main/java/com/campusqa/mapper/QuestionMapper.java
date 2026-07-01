package com.campusqa.mapper;
import com.campusqa.entity.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question(user_id,course_id,title,content,image_url) VALUES(#{userId},#{courseId},#{title},#{content},#{imageUrl})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Question q);

    @Select("SELECT q.*,u.username,c.name as course_name FROM question q LEFT JOIN user u ON q.user_id=u.id LEFT JOIN course c ON q.course_id=c.id WHERE q.id=#{id}")
    Question findById(Long id);

    @Select("<script>SELECT q.*,u.username,c.name as course_name FROM question q LEFT JOIN user u ON q.user_id=u.id LEFT JOIN course c ON q.course_id=c.id WHERE q.status=1 <if test='courseId!=null'>AND q.course_id=#{courseId}</if> <if test='keyword!=null'>AND q.title LIKE CONCAT('%',#{keyword},'%')</if> ORDER BY q.created_at DESC LIMIT #{offset},#{size}</script>")
    List<Question> findList(@Param("courseId") Long courseId, @Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);

    @Select("<script>SELECT COUNT(*) FROM question WHERE status=1 <if test='courseId!=null'>AND course_id=#{courseId}</if> <if test='keyword!=null'>AND title LIKE CONCAT('%',#{keyword},'%')</if></script>")
    int count(@Param("courseId") Long courseId, @Param("keyword") String keyword);

    @Update("UPDATE question SET status=#{status} WHERE id=#{id}")
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);

    @Update("UPDATE question SET answer_count=answer_count+1 WHERE id=#{id}")
    void incrAnswerCount(Long id);

    @Update("UPDATE question SET like_count=like_count+1 WHERE id=#{id}")
    void incrLikeCount(Long id);

    @Update("UPDATE question SET like_count=like_count-1 WHERE id=#{id} AND like_count>0")
    void decrLikeCount(Long id);

    @Select("SELECT q.*,u.username FROM question q LEFT JOIN user u ON q.user_id=u.id WHERE q.user_id=#{userId} ORDER BY q.created_at DESC")
    List<Question> findByUserId(Long userId);

    @Select("SELECT COUNT(*) FROM question")
    int totalCount();

    @Select("SELECT COUNT(*) FROM question WHERE status=1")
    int activeCount();

    @Select("SELECT COUNT(*) FROM question WHERE DATE(created_at)=CURDATE()")
    int todayCount();
}
