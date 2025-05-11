package com.example.todo.service.comment;

import com.example.todo.dto.CommentDto.CommentRequestDto;
import com.example.todo.dto.CommentDto.CommentResponseDto;
import com.example.todo.dto.TodoDto.ScheduleResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(Long authorId, Long scheduleId, Long commentUserId, CommentRequestDto commentRequestDto);

    CommentResponseDto getComment(Long commentId, Long scheduleId, Long commentUserId);

    CommentResponseDto changeComment(Long scheduleId, Long commentUserId, Long commentId, CommentRequestDto requestDto);


    void removeComment(Long scheduleId, Long commentUserId, Long commentId);

}
