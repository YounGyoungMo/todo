package com.example.todo.service.schedule;

import com.example.todo.common.exception.base.CustomException;
import com.example.todo.common.exception.enums.ErrorCode;
import com.example.todo.dto.TodoDto.ScheduleDetailResponseDto;
import com.example.todo.dto.TodoDto.ScheduleRequestDto;
import com.example.todo.dto.TodoDto.ScheduleResponseDto;
import com.example.todo.entity.Schedule;
import com.example.todo.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<ScheduleResponseDto> getSchedules(Long authorId) {
        List<Schedule> schedules = scheduleRepository.findByAuthorIdOrElse(authorId);
        return schedules.stream()
                .map(ScheduleResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDetailResponseDto getSchedule(Long authorId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findByAuthorIdAndIdOrElse(authorId, scheduleId);
        return ScheduleDetailResponseDto.from(schedule);
    }

    @Override
    public ScheduleResponseDto changeSchedule(Long authorId, Long scheduleId, ScheduleRequestDto requestDto) {
        if (requestDto.getTitle().isEmpty() || requestDto.getContents().isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        Schedule schedule = scheduleRepository.findByAuthorIdAndIdOrElse(authorId, scheduleId);
        schedule.update(requestDto.getTitle(), requestDto.getContents());
        return ScheduleResponseDto.from(schedule);
    }

    @Override
    public void removeSchedule(Long authorId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findByAuthorIdAndIdOrElse(authorId, scheduleId);
        scheduleRepository.delete(schedule);
    }

}
