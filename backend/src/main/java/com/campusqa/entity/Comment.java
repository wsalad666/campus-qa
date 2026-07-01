package com.campusqa.entity;
import java.time.LocalDateTime;
public class Comment {
    private Long id; private Long answerId; private Long userId; private String content; private LocalDateTime createdAt;
    private String username;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Long getAnswerId(){return answerId;} public void setAnswerId(Long v){answerId=v;}
    public Long getUserId(){return userId;} public void setUserId(Long v){userId=v;}
    public String getContent(){return content;} public void setContent(String v){content=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
}
