package com.usermanagement.usermanagement.exammodule.repository;

import com.usermanagement.usermanagement.exammodule.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q from Question q where q.test.testId= :testId")
    List<Question> getQuestionsByTestId(Long testId);
}
