package com.example.todo.dto.TodoDto;

import com.example.todo.dto.CommentDto.CommentResponseDto;
import com.example.todo.entity.Schedule;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder

public class ScheduleDetailResponseDto {
    private Long authorId;
    private Long id;
    private String title;
    private String contents;
    private Long commentCount;
    private List<CommentResponseDto> commentList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ScheduleDetailResponseDto from(Schedule schedule) {
        return ScheduleDetailResponseDto.builder()
                .authorId(schedule.getAuthorId())
                .id(schedule.getId())
                .title(schedule.getTitle())
                .commentCount(schedule.getCommentCount())
                .commentList(
                        schedule.getCommentList().stream()
                                .map(comment -> CommentResponseDto.from(schedule, comment))
                                .toList()
                )
                .contents(schedule.getContents())
                .createdAt(schedule.getCreatedAt())
                .updatedAt(schedule.getUpdatedAt())
                .build();
    }

}
