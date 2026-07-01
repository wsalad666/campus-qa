package com.campusqa.entity;
import java.time.LocalDateTime;
public class User {
    private Long id;
    private String studentId;
    private String username;
    private String password;
    private String avatar;
    private String bio;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getStudentId(){return studentId;} public void setStudentId(String v){this.studentId=v;}
    public String getUsername(){return username;} public void setUsername(String v){this.username=v;}
    public String getPassword(){return password;} public void setPassword(String v){this.password=v;}
    public String getAvatar(){return avatar;} public void setAvatar(String v){this.avatar=v;}
    public String getBio(){return bio;} public void setBio(String v){this.bio=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){this.createdAt=v;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){this.updatedAt=v;}
}
