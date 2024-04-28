package com.usermanagement.usermanagement.handlers;

import lombok.Data;

@Data
public class UserException extends RuntimeException {

    private String message;
    private Integer code;
    public UserException(Integer code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    // You can add additional constructors and methods as needed
}
