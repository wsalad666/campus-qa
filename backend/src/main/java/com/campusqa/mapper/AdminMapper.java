package com.campusqa.mapper;
import com.campusqa.entity.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE username=#{username}")
    Admin findByUsername(String username);
}
