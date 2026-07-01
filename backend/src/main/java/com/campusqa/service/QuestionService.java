package com.campusqa.service;
import com.campusqa.entity.*;
import com.campusqa.common.Result;
import java.util.Map;

public interface QuestionService {
    Result<?> publish(Question question);
    Result<Map<String,Object>> list(Long courseId, String keyword, int page, int size);
    Result<Question> detail(Long id);
    Result<?> violation(Long id);
    Result<?> answer(Answer answer);
    Result<?> comment(Comment comment);
    Result<?> acceptAnswer(Long answerId, Long userId);
    Result<?> like(Long questionId, Long userId);
    Result<?> unlike(Long questionId, Long userId);
}
