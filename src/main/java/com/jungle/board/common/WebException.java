package com.jungle.board.common;

import org.springframework.http.HttpStatusCode;

public class WebException extends RuntimeException {
    protected final int code;

    public WebException(HttpStatusCode code) {
        super();
        this.code = code.value();
    }

    public WebException(HttpStatusCode code, String message) {
        super(message);
        this.code = code.value();
    }

    public WebException(String message) {
        super(message);
        this.code = 500;
    }

    public int getCode() {
        return this.code;
    }
}