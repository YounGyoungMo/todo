package com.example.todo.dto.TodoDto;

import com.example.todo.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long userId;
    private Long id;
    private String title;
    private String contents;

    public static ScheduleResponseDto from(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .userId(schedule.getUserId())
                .id(schedule.getId())
                .title(schedule.getTitle())
                .contents(schedule.getContents())
                .build();
    }

}
