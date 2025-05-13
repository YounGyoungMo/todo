package com.example.todo.controller;

import com.example.todo.common.exception.enums.SuccessCode;
import com.example.todo.common.response.ApiResponseDto;
import com.example.todo.dto.commentDto.CommentRequestDto;
import com.example.todo.dto.replyDto.ReplyResponseDto;
import com.example.todo.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    // 대댓글 작성
    @PostMapping
    public ResponseEntity<ApiResponseDto<ReplyResponseDto>> createReply(
            @RequestParam Long scheduleId,
            @RequestParam Long commentUserId,
            @RequestParam Long commentId,
            @RequestBody CommentRequestDto requestDto
    ) {
        ReplyResponseDto responseDto = replyService.createReply(scheduleId,commentUserId, commentId, requestDto);

        return ResponseEntity.status(
                        SuccessCode.REPLY_CREATE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.REPLY_CREATE_SUCCESS, responseDto)
                );
    }

    // 대댓글 수정
    @PutMapping
    public ResponseEntity<ApiResponseDto<ReplyResponseDto>> changeReply(
            @RequestParam Long scheduleId,
            @RequestParam Long commentId,
            @RequestBody CommentRequestDto requestDto
    ) {
        ReplyResponseDto responseDto = replyService.changeReply(scheduleId, commentId, requestDto);
        return ResponseEntity.status(
                        SuccessCode.REPLY_UPDATE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.REPLY_UPDATE_SUCCESS, responseDto)
                );
    }

    // 대댓글 삭제
    @DeleteMapping
    public ResponseEntity<ApiResponseDto<Void>> removeReply(
            @RequestParam Long commentId
    ) {
        replyService.removeReply(commentId);
        return ResponseEntity.status(SuccessCode.REPLY_DELETE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.REPLY_DELETE_SUCCESS, null));
    }
}
