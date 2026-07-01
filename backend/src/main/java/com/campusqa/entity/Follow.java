package com.campusqa.entity;
import java.time.LocalDateTime;
public class Follow {
    private Long id; private Long followerId; private Long followingId; private LocalDateTime createdAt;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Long getFollowerId(){return followerId;} public void setFollowerId(Long v){followerId=v;}
    public Long getFollowingId(){return followingId;} public void setFollowingId(Long v){followingId=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
