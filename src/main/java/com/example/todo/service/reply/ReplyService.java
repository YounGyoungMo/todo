package com.example.todo.service.reply;

import com.example.todo.dto.commentDto.CommentRequestDto;
import com.example.todo.dto.replyDto.ReplyResponseDto;

public interface ReplyService {
   ReplyResponseDto createReply(Long scheduleId, Long replyUserId, Long commentId, CommentRequestDto requestDto);

    ReplyResponseDto changeReply(Long scheduleId, Long commentId, CommentRequestDto requestDto);

    void removeReply(Long commentId);
}
