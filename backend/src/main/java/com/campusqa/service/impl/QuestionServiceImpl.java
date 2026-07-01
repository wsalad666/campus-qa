package com.campusqa.service.impl;
import com.campusqa.common.*;
import com.campusqa.entity.*;
import com.campusqa.mapper.*;
import com.campusqa.service.QuestionService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final CommentMapper commentMapper;
    private final QuestionLikeMapper likeMapper;

    public QuestionServiceImpl(QuestionMapper qm, AnswerMapper am, CommentMapper cm, QuestionLikeMapper lm) {
        questionMapper=qm; answerMapper=am; commentMapper=cm; likeMapper=lm;
    }

    public Result<?> publish(Question q) {
        questionMapper.insert(q);
        return Result.success(q);
    }

    public Result<Map<String,Object>> list(Long courseId, String keyword, int page, int size) {
        int offset = (page-1)*size;
        List<Question> list = questionMapper.findList(courseId, keyword, offset, size);
        int total = questionMapper.count(courseId, keyword);
        Map<String,Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", total);
        map.put("page", page);
        return Result.success(map);
    }

    public Result<Question> detail(Long id) {
        Question q = questionMapper.findById(id);
        if (q == null) return Result.error(404, "问题不存在");
        List<Answer> answers = answerMapper.findByQuestionId(id);
        q.setAnswerCount(answers.size());
        return Result.success(q);
    }

    public Result<?> violation(Long id) {
        questionMapper.updateStatus(id, 0);
        return Result.success();
    }

    public Result<?> answer(Answer a) {
        answerMapper.insert(a);
        questionMapper.incrAnswerCount(a.getQuestionId());
        return Result.success();
    }

    public Result<?> comment(Comment c) {
        commentMapper.insert(c);
        return Result.success();
    }

    public Result<?> acceptAnswer(Long answerId, Long userId) {
        Answer a = answerMapper.findById(answerId);
        if (a == null) return Result.error(404, "回答不存在");
        Question q = questionMapper.findById(a.getQuestionId());
        if (!q.getUserId().equals(userId)) return Result.error(403, "只有提问者才能采纳");
        answerMapper.accept(answerId);
        return Result.success();
    }

    public Result<?> like(Long questionId, Long userId) {
        if (likeMapper.exists(questionId, userId)) return Result.error(400, "已点赞");
        QuestionLike l = new QuestionLike(); l.setQuestionId(questionId); l.setUserId(userId);
        likeMapper.insert(l);
        questionMapper.incrLikeCount(questionId);
        return Result.success();
    }

    public Result<?> unlike(Long questionId, Long userId) {
        likeMapper.delete(questionId, userId);
        questionMapper.decrLikeCount(questionId);
        return Result.success();
    }
}
