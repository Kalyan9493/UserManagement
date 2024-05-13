package com.usermanagement.usermanagement.exammodule.controller;

import com.usermanagement.usermanagement.dto.ResponseDTO;
import com.usermanagement.usermanagement.exammodule.model.Question;
import com.usermanagement.usermanagement.exammodule.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseDTO getAllQuestions() {

        try {
            List<Question> questions = questionService.getAllQuestions();
            return new ResponseDTO("success", HttpStatus.OK.value(), questions);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO getQuestionById(@PathVariable Long id) {

        try {
            Question question = questionService.getQuestionById(id);
            return new ResponseDTO("success", HttpStatus.OK.value(), question);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @PostMapping
    public ResponseDTO createQuestion(@RequestBody Question question) {

        try {
            question = questionService.saveQuestion(question);
            return new ResponseDTO("success", HttpStatus.OK.value(), question);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteQuestion(@PathVariable Long id) {

        try {
            questionService.deleteQuestion(id);
            return new ResponseDTO("success", HttpStatus.OK.value(), null);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @GetMapping("/test/{testId}")
    public ResponseDTO getQuestionsByTestId(@PathVariable Long testId) {
        try {
            List<Question> questions = questionService.getQuestionsByTestId(testId);
            return new ResponseDTO("success", HttpStatus.OK.value(), questions);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }
}
