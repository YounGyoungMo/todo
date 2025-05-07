package com.example.todo.common.exception.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum SuccessCode {

    // 일정 관련 성공 코드
    SCHEDULE_CREATE_SUCCESS(HttpStatus.CREATED, "일정 생성이 성공적으로 처리되었습니다. "),
    SCHEDULE_LIST_SUCCESS(HttpStatus.OK, "전체 일정이 성공적으로 조회되었습니다."),
    SCHEDULE_SUCCESS(HttpStatus.OK, "해당 일정이 성공적으로 조회되었습니다."),
    SCHEDULE_UPDATE_SUCCESS(HttpStatus.OK, "일정 수정이 성공적으로 처리되었습니다. "),
    SCHEDULE_DELETE_SUCCESS(HttpStatus.OK, "일정 삭제가 성공적으로 처리되었습니다. "),

    // 댓글 관련 성공 코드
    COMMENT_CREATE_SUCCESS(HttpStatus.CREATED, "댓글 작성이 성공적으로 처리되었습니다. "),
    REPLY_COMMENT_CREATE_SUCCESS(HttpStatus.CREATED, "대댓글 작성이 성공적으로 처리되었습니다. "),


    ;

    private final HttpStatus httpStatus;
    private final String message;

}
