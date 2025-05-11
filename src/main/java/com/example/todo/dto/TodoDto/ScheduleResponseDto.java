package com.example.todo.dto.TodoDto;

import com.example.todo.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long userId;
    private Long id;
    private String title;
    private String contents;
    private Long commentCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ScheduleResponseDto from(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .userId(schedule.getAuthorId())
                .id(schedule.getId())
                .title(schedule.getTitle())
                .commentCount(schedule.getCommentCount())
                .contents(schedule.getContents())
                .createdAt(schedule.getCreatedAt())
                .updatedAt(schedule.getUpdatedAt())
                .build();
    }

}
