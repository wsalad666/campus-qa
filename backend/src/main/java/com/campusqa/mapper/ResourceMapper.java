package com.campusqa.mapper;
import com.campusqa.entity.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ResourceMapper {
    @Insert("INSERT INTO resource(user_id,course_id,title,filename,file_path,file_size,file_type) VALUES(#{userId},#{courseId},#{title},#{filename},#{filePath},#{fileSize},#{fileType})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Resource r);

    @Select("SELECT r.*,u.username,c.name as course_name FROM resource r LEFT JOIN user u ON r.user_id=u.id LEFT JOIN course c ON r.course_id=c.id WHERE r.id=#{id}")
    Resource findById(Long id);

    @Select("<script>SELECT r.*,u.username,c.name as course_name FROM resource r LEFT JOIN user u ON r.user_id=u.id LEFT JOIN course c ON r.course_id=c.id <where><if test='courseId!=null'>r.course_id=#{courseId}</if></where> ORDER BY r.created_at DESC LIMIT #{offset},#{size}</script>")
    List<Resource> findList(@Param("courseId") Long courseId, @Param("offset") int offset, @Param("size") int size);

    @Select("<script>SELECT COUNT(*) FROM resource <where><if test='courseId!=null'>course_id=#{courseId}</if></where></script>")
    int count(@Param("courseId") Long courseId);

    @Delete("DELETE FROM resource WHERE id=#{id}")
    void delete(Long id);

    @Update("UPDATE resource SET download_count=download_count+1 WHERE id=#{id}")
    void incrDownloadCount(Long id);

    @Select("SELECT COUNT(*) FROM resource")
    int totalCount();
}
