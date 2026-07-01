package com.campusqa.entity;
import java.time.LocalDateTime;
public class Favorite {
    private Long id; private Long userId; private Long questionId; private Long resourceId; private LocalDateTime createdAt;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Long getUserId(){return userId;} public void setUserId(Long v){userId=v;}
    public Long getQuestionId(){return questionId;} public void setQuestionId(Long v){questionId=v;}
    public Long getResourceId(){return resourceId;} public void setResourceId(Long v){resourceId=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
