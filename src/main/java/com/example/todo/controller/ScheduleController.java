package com.example.todo.controller;

import com.example.todo.common.exception.enums.SuccessCode;
import com.example.todo.common.response.ApiResponseDto;
import com.example.todo.dto.TodoDto.ScheduleRequestDto;
import com.example.todo.dto.TodoDto.ScheduleResponseDto;
import com.example.todo.service.ScheduleService;
import com.example.todo.service.ScheduleServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정 관리 컨트롤러
 * 일정 생성, 조회, 수정, 삭제 기능
 **/
@RestController
@Validated
@RequestMapping("/users/{userId}/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleServiceImpl scheduleService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<ScheduleResponseDto>> createSchedule(@RequestBody ScheduleRequestDto requestDto, @PathVariable Long userId) {
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto, userId);
        return ResponseEntity.status(
                SuccessCode.SCHEDULE_CREATE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.SCHEDULE_CREATE_SUCCESS, responseDto)
        );
    }

    // 일정 전체 조회
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ScheduleResponseDto>>> getSchedules(@PathVariable Long userId) {
        List<ScheduleResponseDto> responseDto = scheduleService.getSchedules(userId);
        return ResponseEntity.status(SuccessCode.SCHEDULE_LIST_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.SCHEDULE_LIST_SUCCESS, responseDto));
    }

    // 단일 일정(상세) 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ApiResponseDto<ScheduleResponseDto>> getSchedule(@PathVariable Long userId, @PathVariable Long scheduleId){
        ScheduleResponseDto responseDto = scheduleService.getSchedule(userId, scheduleId);
        return ResponseEntity.status(SuccessCode.SCHEDULE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.SCHEDULE_SUCCESS, responseDto));
    }

    // 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ApiResponseDto<ScheduleResponseDto>> changeSchedule(@PathVariable Long userId, @PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto){
        ScheduleResponseDto responseDto = scheduleService.changeSchedule(userId, scheduleId, requestDto);
        return ResponseEntity.status(SuccessCode.SCHEDULE_UPDATE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode. SCHEDULE_UPDATE_SUCCESS, responseDto));
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ApiResponseDto<Void>> removeSchedule(@PathVariable Long userId, @PathVariable Long scheduleId) {
        scheduleService.removeSchedule(userId, scheduleId);
        return ResponseEntity.status(SuccessCode.SCHEDULE_DELETE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode. SCHEDULE_DELETE_SUCCESS, null));
    }

}
