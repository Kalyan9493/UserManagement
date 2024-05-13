package com.usermanagement.usermanagement.exammodule.service;

import com.usermanagement.usermanagement.exammodule.model.Test;

import java.util.List;

public interface TestService {
    List<Test> getAllTests();
    Test getTestById(Long id);
    Test saveTest(Test test);
    void deleteTest(Long id);
}
