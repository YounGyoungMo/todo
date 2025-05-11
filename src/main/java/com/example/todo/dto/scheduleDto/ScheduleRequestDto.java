package com.example.todo.dto.scheduleDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    @NotNull(message = "제목은 필수입니다.")
    private String title;

    @NotNull(message = "내용은 필수입니다.")
    private String contents;


}
