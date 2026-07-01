package com.campusqa.entity;
import java.time.LocalDateTime;
public class Admin {
    private Long id; private String username; private String password; private LocalDateTime createdAt;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
