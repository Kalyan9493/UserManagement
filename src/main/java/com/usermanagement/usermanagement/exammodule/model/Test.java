package com.usermanagement.usermanagement.exammodule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long testId;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "test_date")
    private String testDate;

    @Column(name = "test_duration")
    private String testDuration;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

}