package com.usermanagement.usermanagement.exammodule.controller;

import com.usermanagement.usermanagement.dto.ResponseDTO;
import com.usermanagement.usermanagement.exammodule.model.Answer;
import com.usermanagement.usermanagement.exammodule.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public ResponseDTO getAllAnswers() {
        try {
            List<Answer> answers = answerService.getAllAnswers();
            return new ResponseDTO("success", HttpStatus.OK.value(), answers);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO getAnswerById(@PathVariable Long id) {

        try {
            Answer answer = answerService.getAnswerById(id);
            return new ResponseDTO("success", HttpStatus.OK.value(), answer);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @PostMapping
    public ResponseDTO createAnswer(@RequestBody Answer answer) {

        try {
            answer = answerService.saveAnswer(answer);
            return new ResponseDTO("success", HttpStatus.OK.value(), answer);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteAnswer(@PathVariable Long id) {

        try {
            answerService.deleteAnswer(id);
            return new ResponseDTO("success", HttpStatus.OK.value(), null);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }
}
