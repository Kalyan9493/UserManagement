package com.usermanagement.usermanagement.exammodule.service;

import com.usermanagement.usermanagement.exammodule.model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
    Question getQuestionById(Long id);
    Question saveQuestion(Question question);
    void deleteQuestion(Long id);
    List<Question> getQuestionsByTestId(Long testId);
}
