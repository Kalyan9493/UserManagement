package com.usermanagement.usermanagement.exammodule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "is_correct")
    @JsonIgnore
    private boolean isCorrect;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private Question question;

}
