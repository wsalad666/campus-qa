package com.campusqa.controller;

import com.campusqa.common.*;
import com.campusqa.entity.*;
import com.campusqa.service.ResourceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/api/resources")
@Api(tags = "D-资源模块")
public class ResourceController {
    private final ResourceService resourceService;

    @Value("${file.upload-path}")
    private String uploadPath;

    public ResourceController(ResourceService rs) { resourceService = rs; }

    @PostMapping("/upload")
    @ApiOperation("上传资料")
    public Result<?> upload(@RequestParam("file") MultipartFile file,
                            @RequestParam Long userId,
                            @RequestParam Long courseId,
                            @RequestParam String title) throws IOException {
        Path dir = Paths.get(uploadPath);
        if (!Files.exists(dir)) Files.createDirectories(dir);
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), dir.resolve(filename));

        Resource r = new Resource();
        r.setUserId(userId); r.setCourseId(courseId); r.setTitle(title);
        r.setFilename(file.getOriginalFilename()); r.setFilePath(uploadPath + "/" + filename);
        r.setFileSize(file.getSize());
        String type = file.getOriginalFilename();
        r.setFileType(type != null && type.contains(".") ? type.substring(type.lastIndexOf(".")) : "");
        return resourceService.upload(r);
    }

    @GetMapping
    @ApiOperation("资源列表")
    public Result<Map<String,Object>> list(
            @RequestParam(required=false) Long courseId,
            @RequestParam(defaultValue="1") int page,
            @RequestParam(defaultValue="10") int size) {
        return resourceService.list(courseId, page, size);
    }

    @GetMapping("/{id}/download")
    @ApiOperation("下载资源")
    public Result<?> download(@PathVariable Long id) {
        return resourceService.download(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除资源")
    public Result<?> delete(@PathVariable Long id, @RequestParam Long userId) {
        return resourceService.delete(id, userId);
    }
}
