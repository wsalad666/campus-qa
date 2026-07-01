package com.campusqa.entity;
import java.time.LocalDateTime;
public class Resource {
    private Long id; private Long userId; private Long courseId;
    private String title; private String filename; private String filePath;
    private Long fileSize; private String fileType; private Integer downloadCount;
    private LocalDateTime createdAt;
    private String username; private String courseName;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Long getUserId(){return userId;} public void setUserId(Long v){userId=v;}
    public Long getCourseId(){return courseId;} public void setCourseId(Long v){courseId=v;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getFilename(){return filename;} public void setFilename(String v){filename=v;}
    public String getFilePath(){return filePath;} public void setFilePath(String v){filePath=v;}
    public Long getFileSize(){return fileSize;} public void setFileSize(Long v){fileSize=v;}
    public String getFileType(){return fileType;} public void setFileType(String v){fileType=v;}
    public Integer getDownloadCount(){return downloadCount;} public void setDownloadCount(Integer v){downloadCount=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getCourseName(){return courseName;} public void setCourseName(String v){courseName=v;}
}
