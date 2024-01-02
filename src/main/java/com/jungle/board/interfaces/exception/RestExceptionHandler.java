package com.jungle.board.interfaces.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jungle.board.interfaces.exception.model.ExceptionResponse;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException e) {
        int code = 500;
        var response = ExceptionResponse.builder().code(code).message("Internal Server Error").build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(code));
    }
}
