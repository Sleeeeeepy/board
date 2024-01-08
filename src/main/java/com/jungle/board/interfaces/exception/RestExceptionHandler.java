package com.jungle.board.interfaces.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jungle.board.common.WebException;
import com.jungle.board.interfaces.exception.model.ExceptionResponse;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(WebException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> handleRuntimeException(WebException e) {
        int code = e.getCode();
        var response = ExceptionResponse.builder().code(code).message(e.getMessage()).build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(code));
    }
}
