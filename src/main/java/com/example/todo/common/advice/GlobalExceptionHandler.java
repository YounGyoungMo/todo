package com.example.todo.common.advice;

import com.example.todo.common.exception.base.CustomException;
import com.example.todo.common.exception.enums.ErrorCode;
import com.example.todo.common.response.ApiResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * DTO 유효성 검증 실패 시 Validated
     * - @Valid 어노테이션이 붙은 DTO 필드 검증 실패 시 발생
     * - 예: @NotBlank, @Size 등 제약 조건을 위반한 경우
     * - 사용자가 설정한 message 값을 추출하여 클라이언트에게 응답
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<?>> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        String errorMessage = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDto.fail(errorMessage, httpServletRequest.getRequestURI()));
    }

    /**
     * 커스텀 예외(CustomException)를 처리하는 핸들러
     * - 개발자가 정의한 도메인/비즈니스 예외
     * - 예: 일정 생성 실패 등
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponseDto<?>> handleCustomException(CustomException e, HttpServletRequest httpServletRequest) {

        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponseDto.fail(e.getErrorCode(), httpServletRequest.getRequestURI()));

    }



}
