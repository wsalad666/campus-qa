package com.campusqa.entity;
import java.time.LocalDateTime;
public class Course {
    private Long id; private String name; private String description; private LocalDateTime createdAt;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
