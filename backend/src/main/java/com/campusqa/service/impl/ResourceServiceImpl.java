package com.campusqa.service.impl;
import com.campusqa.common.*;
import com.campusqa.entity.*;
import com.campusqa.mapper.*;
import com.campusqa.service.ResourceService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceMapper resourceMapper;

    public ResourceServiceImpl(ResourceMapper rm) { resourceMapper=rm; }

    public Result<?> upload(Resource r) {
        resourceMapper.insert(r);
        return Result.success(r);
    }

    public Result<Map<String,Object>> list(Long courseId, int page, int size) {
        int offset = (page-1)*size;
        List<Resource> list = resourceMapper.findList(courseId, offset, size);
        int total = resourceMapper.count(courseId);
        Map<String,Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", total);
        return Result.success(map);
    }

    public Result<?> delete(Long id, Long userId) {
        Resource r = resourceMapper.findById(id);
        if (r == null) return Result.error(404, "资源不存在");
        if (!r.getUserId().equals(userId)) return Result.error(403, "只能删除自己的资源");
        resourceMapper.delete(id);
        return Result.success();
    }

    public Result<?> download(Long id) {
        Resource r = resourceMapper.findById(id);
        if (r == null) return Result.error(404, "资源不存在");
        resourceMapper.incrDownloadCount(id);
        return Result.success(r);
    }
}
