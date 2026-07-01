package com.campusqa.service;
import com.campusqa.entity.*;
import com.campusqa.common.Result;
import java.util.Map;

public interface ResourceService {
    Result<?> upload(Resource resource);
    Result<Map<String,Object>> list(Long courseId, int page, int size);
    Result<?> delete(Long id, Long userId);
    Result<?> download(Long id);
}
