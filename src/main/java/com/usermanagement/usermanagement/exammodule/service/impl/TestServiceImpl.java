package com.usermanagement.usermanagement.exammodule.service.impl;


import com.usermanagement.usermanagement.exammodule.model.Test;
import com.usermanagement.usermanagement.exammodule.repository.TestRepository;
import com.usermanagement.usermanagement.exammodule.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @Override
    public Test getTestById(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    @Override
    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
