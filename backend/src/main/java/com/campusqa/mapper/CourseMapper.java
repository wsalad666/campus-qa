package com.campusqa.mapper;
import com.campusqa.entity.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("SELECT * FROM course")
    List<Course> findAll();

    @Select("SELECT * FROM course WHERE id=#{id}")
    Course findById(Long id);

    @Insert("INSERT INTO course(name,description) VALUES(#{name},#{description})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Course course);

    @Update("UPDATE course SET name=#{name},description=#{description} WHERE id=#{id}")
    void update(Course course);

    @Delete("DELETE FROM course WHERE id=#{id}")
    void delete(Long id);
}
