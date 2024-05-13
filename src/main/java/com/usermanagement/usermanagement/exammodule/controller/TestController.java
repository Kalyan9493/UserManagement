package com.usermanagement.usermanagement.exammodule.controller;

import com.usermanagement.usermanagement.dto.ResponseDTO;
import com.usermanagement.usermanagement.exammodule.model.Test;
import com.usermanagement.usermanagement.exammodule.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public ResponseDTO getAllTests() {

        try {
            List<Test> tests = testService.getAllTests();
            return new ResponseDTO("success", HttpStatus.OK.value(), tests);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO getTestById(@PathVariable Long id) {

        try {
            Test test = testService.getTestById(id);
            return new ResponseDTO("success", HttpStatus.OK.value(), test);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @PostMapping
    public ResponseDTO createTest(@RequestBody Test test) {

        try {
            test = testService.saveTest(test);
            return new ResponseDTO("success", HttpStatus.OK.value(), test);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteTest(@PathVariable Long id) {

        try {
            testService.deleteTest(id);
            return new ResponseDTO("success", HttpStatus.OK.value(), null);
        } catch (Exception ex) {
            return new ResponseDTO("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
    }
}
