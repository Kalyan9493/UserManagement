package com.usermanagement.usermanagement.exammodule.service.impl;

import com.usermanagement.usermanagement.exammodule.model.Question;
import com.usermanagement.usermanagement.exammodule.repository.QuestionRepository;
import com.usermanagement.usermanagement.exammodule.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getQuestionsByTestId(Long testId) {
        return questionRepository.getQuestionsByTestId(testId);
    }
}
