package com.usermanagement.usermanagement.exammodule.repository;
import com.usermanagement.usermanagement.exammodule.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}