package com.campusqa.mapper;
import com.campusqa.entity.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(student_id,username,password) VALUES(#{studentId},#{username},#{password})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(User user);

    @Select("SELECT * FROM user WHERE student_id=#{studentId}")
    User findByStudentId(String studentId);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User findById(Long id);

    @Select("SELECT * FROM user WHERE username LIKE CONCAT('%',#{keyword},'%')")
    List<User> search(String keyword);

    @Update("UPDATE user SET username=#{username},avatar=#{avatar},bio=#{bio} WHERE id=#{id}")
    void update(User user);
}
