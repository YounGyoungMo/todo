package com.example.todo.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    CANT_FIND_SCHEDULE(HttpStatus.NOT_FOUND,"일정이 존재하지 않습니다."),
    CANT_FIND_THIS_SCHEDULE(HttpStatus.NOT_FOUND, "해당 일정을 찾을 수 없습니다."), 
    CANT_FIND_COMMENT(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.")
    
    ;
    
    // 일정 관련 에러


    private final HttpStatus httpStatus;
    private final String message;

}
