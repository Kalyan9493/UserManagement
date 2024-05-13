package com.usermanagement.usermanagement.exammodule.service;

import com.usermanagement.usermanagement.exammodule.model.Answer;

import java.util.List;

public interface AnswerService {

    List<Answer> getAllAnswers();
    Answer getAnswerById(Long id);
    Answer saveAnswer(Answer answer);
    void deleteAnswer(Long id);
}
