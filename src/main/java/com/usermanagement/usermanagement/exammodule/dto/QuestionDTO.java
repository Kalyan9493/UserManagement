package com.usermanagement.usermanagement.exammodule.dto;

import com.usermanagement.usermanagement.exammodule.model.Answer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDTO {

    private Long questionId;
    private String questionText;
    private List<Answer> answers = new ArrayList<>();
}
