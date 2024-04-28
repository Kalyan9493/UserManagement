package com.usermanagement.usermanagement.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private String status;
    private Object data;
    private Integer code;
    private String message;

    public ResponseDTO(String status, Integer code, Object data){
        this.status = status;
        this.data = data;
        this.code = code;
    }
    public ResponseDTO(String status, Integer code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
