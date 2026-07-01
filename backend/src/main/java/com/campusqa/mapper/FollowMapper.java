package com.campusqa.mapper;
import com.campusqa.entity.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface FollowMapper {
    @Insert("INSERT INTO follow(follower_id,following_id) VALUES(#{followerId},#{followingId})")
    void insert(Follow follow);

    @Delete("DELETE FROM follow WHERE follower_id=#{followerId} AND following_id=#{followingId}")
    void delete(@Param("followerId") Long followerId, @Param("followingId") Long followingId);

    @Select("SELECT COUNT(*) FROM follow WHERE follower_id=#{userId}")
    int countFollowing(Long userId);

    @Select("SELECT COUNT(*) FROM follow WHERE following_id=#{userId}")
    int countFollowers(Long userId);

    @Select("SELECT * FROM follow WHERE follower_id=#{userId}")
    List<Follow> findByFollowerId(Long userId);

    @Select("SELECT EXISTS(SELECT 1 FROM follow WHERE follower_id=#{followerId} AND following_id=#{followingId})")
    boolean exists(@Param("followerId") Long followerId, @Param("followingId") Long followingId);
}
