package com.example.todo.controller;

import com.example.todo.common.exception.enums.SuccessCode;
import com.example.todo.common.response.ApiResponseDto;
import com.example.todo.dto.commentDto.CommentRequestDto;
import com.example.todo.dto.commentDto.CommentResponseDto;
import com.example.todo.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<CommentResponseDto>> createComment(
            @RequestParam Long authorId,
            @RequestParam Long scheduleId,
            @RequestParam Long commentUserId,
            @RequestBody CommentRequestDto requestDto
    ) {
        CommentResponseDto responseDto = commentService.createComment(authorId, scheduleId,commentUserId, requestDto);
        return ResponseEntity.status(
                        SuccessCode.COMMENT_CREATE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.COMMENT_CREATE_SUCCESS, responseDto)
                );
    }
    // 댓글 조회
    @GetMapping
    public ResponseEntity<ApiResponseDto<CommentResponseDto>> getComment(
            @RequestParam Long scheduleId,
            @RequestParam Long commentUserId,
            @RequestParam Long commentId
    ){
        CommentResponseDto responseDto = commentService.getComment(scheduleId, commentUserId, commentId);

        return ResponseEntity.status(SuccessCode.COMMENT_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.COMMENT_SUCCESS, responseDto));
    }

    // 댓글 수정
    @PutMapping
    public ResponseEntity<ApiResponseDto<CommentResponseDto>> changeComment(
            @RequestParam Long scheduleId,
            @RequestParam Long commentUserId,
            @RequestParam Long commentId,
            @RequestBody CommentRequestDto requestDto)
    {
        CommentResponseDto responseDto = commentService.changeComment(scheduleId, commentUserId, commentId, requestDto);
        return ResponseEntity.status(SuccessCode.COMMENT_UPDATE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode. COMMENT_UPDATE_SUCCESS, responseDto));

    }

    // 댓글 삭제
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<Void>> removeComment(
            @RequestParam Long scheduleId,
            @RequestParam Long commentUserId,
            @RequestParam Long commentId
    ) {
        commentService.removeComment(scheduleId, commentUserId, commentId);
        return ResponseEntity.status(SuccessCode.COMMENT_DELETE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.COMMENT_DELETE_SUCCESS, null));
    }



}
