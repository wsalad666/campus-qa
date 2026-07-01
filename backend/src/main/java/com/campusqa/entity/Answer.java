package com.campusqa.entity;
import java.time.LocalDateTime;
public class Answer {
    private Long id; private Long questionId; private Long userId;
    private String content; private Integer isAccepted; private Integer likeCount;
    private LocalDateTime createdAt; private LocalDateTime updatedAt;
    private String username;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Long getQuestionId(){return questionId;} public void setQuestionId(Long v){questionId=v;}
    public Long getUserId(){return userId;} public void setUserId(Long v){userId=v;}
    public String getContent(){return content;} public void setContent(String v){content=v;}
    public Integer getIsAccepted(){return isAccepted;} public void setIsAccepted(Integer v){isAccepted=v;}
    public Integer getLikeCount(){return likeCount;} public void setLikeCount(Integer v){likeCount=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){updatedAt=v;}
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
}
