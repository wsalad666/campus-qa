package com.campusqa.entity;
import java.time.LocalDateTime;
public class Question {
    private Long id; private Long userId; private Long courseId;
    private String title; private String content; private String imageUrl;
    private Integer status; private Integer likeCount; private Integer answerCount;
    private Integer isResolved; private LocalDateTime createdAt; private LocalDateTime updatedAt;
    private String username; private String courseName;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Long getUserId(){return userId;} public void setUserId(Long v){userId=v;}
    public Long getCourseId(){return courseId;} public void setCourseId(Long v){courseId=v;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getContent(){return content;} public void setContent(String v){content=v;}
    public String getImageUrl(){return imageUrl;} public void setImageUrl(String v){imageUrl=v;}
    public Integer getStatus(){return status;} public void setStatus(Integer v){status=v;}
    public Integer getLikeCount(){return likeCount;} public void setLikeCount(Integer v){likeCount=v;}
    public Integer getAnswerCount(){return answerCount;} public void setAnswerCount(Integer v){answerCount=v;}
    public Integer getIsResolved(){return isResolved;} public void setIsResolved(Integer v){isResolved=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){updatedAt=v;}
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getCourseName(){return courseName;} public void setCourseName(String v){courseName=v;}
}
