package com.jungle.board.interfaces.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ExceptionResponse {
    private final int code;
    private final String message;
}
