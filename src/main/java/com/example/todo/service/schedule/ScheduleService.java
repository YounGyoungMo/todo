package com.example.todo.service.schedule;

import com.example.todo.dto.scheduleDto.ScheduleDetailResponseDto;
import com.example.todo.dto.scheduleDto.ScheduleRequestDto;
import com.example.todo.dto.scheduleDto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto,Long userId);
    List<ScheduleResponseDto> getSchedules(Long userId);
    ScheduleDetailResponseDto getSchedule(Long userId, Long scheduleId);

    ScheduleResponseDto changeSchedule(Long userId, Long scheduleId, ScheduleRequestDto requestDto);

    void removeSchedule(Long userId, Long scheduleId);

}
