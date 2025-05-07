package com.example.todo.service;

import com.example.todo.common.exception.base.CustomException;
import com.example.todo.common.exception.enums.ErrorCode;
import com.example.todo.dto.TodoDto.ScheduleRequestDto;
import com.example.todo.dto.TodoDto.ScheduleResponseDto;
import com.example.todo.entity.Schedule;
import com.example.todo.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, Long userId) {

        Schedule schedule = Schedule.of(userId, requestDto.getTitle(), requestDto.getContents());
        scheduleRepository.save(schedule);

        return ScheduleResponseDto.from(schedule);

    }

    @Override
    public List<ScheduleResponseDto> getSchedules(Long userId) {
        List<Schedule> schedules = scheduleRepository.findByUserIdOrElse(userId);
        return schedules.stream()
                .map(ScheduleResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDto getSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findByUserIdAndIdOrElse(userId, scheduleId);
        return ScheduleResponseDto.from(schedule);
    }

    @Override
    public ScheduleResponseDto changeSchedule(Long userId, Long scheduleId, ScheduleRequestDto requestDto) {
        if (requestDto.getTitle().isEmpty() || requestDto.getContents().isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        Schedule schedule = scheduleRepository.findByUserIdAndIdOrElse(userId, scheduleId);
        schedule.update(requestDto.getTitle(), requestDto.getContents());
        return ScheduleResponseDto.from(schedule);
    }

    @Override
    public void removeSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findByUserIdAndIdOrElse(userId, scheduleId);
        scheduleRepository.delete(schedule);
    }


}
