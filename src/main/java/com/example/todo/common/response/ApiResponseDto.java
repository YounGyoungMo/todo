package com.example.todo.common.response;

import com.example.todo.common.exception.enums.ErrorCode;
import com.example.todo.common.exception.enums.SuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.micrometer.common.lang.Nullable;
import lombok.Builder;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.net.http.HttpClient;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
public record ApiResponseDto<T>( // record: dto를 간결하게 작성하는 방식으로 생성자/getter/equals 를 자동으로 만들어줌, 불변객체임
                                 @JsonInclude(NON_NULL) LocalDateTime timestamp,                       // 요청 시각
                                 int statusCode,                                // HTTP 상태 코드 숫자 (예: 400)
                                 @NonNull String message,                       // 응답 메시지 (ex: "가게 생성 성공", "잘못된 요청입니다")
                                 @JsonInclude(value = NON_NULL) String path,// 요청 경로 (ex : api/stores)
                                 @JsonInclude(value = NON_NULL) T data// 실제 응답 데이터 (nullable), null이면 JSON에서 제외됨
) {
    /**
     * 성공 응답을 생성하는 메소드
     * 예를 들어 일정 생성에 성공했을 때 데이터도 함께 반환
     * @param successCode 성공 상태코드/메시지 Enum
     * @param data 응답 데이터
     * @return ApiResponseDto
     */
    public static <T> ApiResponseDto<T> success(final SuccessCode successCode,
                                                @Nullable final T data
    ) {
        return new ApiResponseDto<>(
                null,
                successCode.getHttpStatus().value(),
                successCode.getMessage(),
                null,
                data
        );
    }

    /**
     * 실패 응답을 생성하는 메소드 (ErrorCode 기반)
     * 예를 들어 일정 생성에 실패했을 때 상태코드, 에러 메시지, 경로 등을 반환
     * @param errorCode 에러 코드 Enum
     * @param path      요청 경로
     * @return ApiResponseDto
     */
    public static <T> ApiResponseDto<T> fail(final ErrorCode errorCode, final String path) {
        return new ApiResponseDto<>(
                LocalDateTime.now(),
                errorCode.getHttpStatus().value(),
                errorCode.getMessage(),
                path,
                null
        );
    }

    public static <T> ApiResponseDto<T> fail(String errorMessage, final String path) {
        return new ApiResponseDto<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                errorMessage,
                path,
                null
        );
    }
}
