package com.usermanagement.usermanagement.exammodule.repository;

import com.usermanagement.usermanagement.exammodule.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
