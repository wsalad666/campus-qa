package com.campusqa.controller;

import com.campusqa.common.*;
import com.campusqa.entity.*;
import com.campusqa.service.QuestionService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
@Api(tags = "C-问答模块")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService qs) { questionService = qs; }

    @PostMapping
    @ApiOperation("发布问题（支持图文）")
    public Result<?> publish(@RequestBody Question question) {
        return questionService.publish(question);
    }

    @GetMapping
    @ApiOperation("问题列表（分页+课程筛选+搜索）")
    public Result<Map<String,Object>> list(
            @RequestParam(required=false) Long courseId,
            @RequestParam(required=false) String keyword,
            @RequestParam(defaultValue="1") int page,
            @RequestParam(defaultValue="10") int size) {
        return questionService.list(courseId, keyword, page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("问题详情（含回答列表）")
    public Result<Question> detail(@PathVariable Long id) {
        return questionService.detail(id);
    }

    @PutMapping("/{id}/violation")
    @ApiOperation("违规下架（供后台管理调用）")
    public Result<?> violation(@PathVariable Long id) {
        return questionService.violation(id);
    }

    @PostMapping("/{id}/answers")
    @ApiOperation("回答问题")
    public Result<?> answer(@PathVariable Long id, @RequestBody Answer answer) {
        answer.setQuestionId(id);
        return questionService.answer(answer);
    }

    @PostMapping("/answers/{answerId}/comments")
    @ApiOperation("评论回答")
    public Result<?> comment(@PathVariable Long answerId, @RequestBody Comment comment) {
        comment.setAnswerId(answerId);
        return questionService.comment(comment);
    }

    @PutMapping("/answers/{answerId}/accept")
    @ApiOperation("采纳答案")
    public Result<?> accept(@PathVariable Long answerId, @RequestParam Long userId) {
        return questionService.acceptAnswer(answerId, userId);
    }

    @PostMapping("/{id}/like")
    @ApiOperation("点赞问题")
    public Result<?> like(@PathVariable Long id, @RequestParam Long userId) {
        return questionService.like(id, userId);
    }

    @DeleteMapping("/{id}/like")
    @ApiOperation("取消点赞")
    public Result<?> unlike(@PathVariable Long id, @RequestParam Long userId) {
        return questionService.unlike(id, userId);
    }
}
