package com.usermanagement.usermanagement.exammodule.service.impl;

import com.usermanagement.usermanagement.exammodule.model.Answer;
import com.usermanagement.usermanagement.exammodule.repository.AnswerRepository;
import com.usermanagement.usermanagement.exammodule.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}
