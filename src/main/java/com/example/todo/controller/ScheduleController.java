package com.example.todo.controller;

import com.example.todo.common.exception.enums.SuccessCode;
import com.example.todo.common.response.ApiResponseDto;
import com.example.todo.dto.TodoDto.ScheduleDetailResponseDto;
import com.example.todo.dto.TodoDto.ScheduleRequestDto;
import com.example.todo.dto.TodoDto.ScheduleResponseDto;
import com.example.todo.service.schedule.ScheduleServiceImpl;
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
@RequestMapping("/authors/{authorId}/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleServiceImpl scheduleService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<ScheduleResponseDto>> createSchedule(@RequestBody ScheduleRequestDto requestDto, @PathVariable Long authorId) {
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto, authorId);
        return ResponseEntity.status(
                SuccessCode.SCHEDULE_CREATE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.SCHEDULE_CREATE_SUCCESS, responseDto)
        );
    }

    // 일정 전체 조회
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ScheduleResponseDto>>> getSchedules(@PathVariable Long authorId) {
        List<ScheduleResponseDto> responseDto = scheduleService.getSchedules(authorId);
        return ResponseEntity.status(SuccessCode.SCHEDULE_LIST_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.SCHEDULE_LIST_SUCCESS, responseDto));
    }

    // 단일 일정(상세) 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ApiResponseDto<ScheduleDetailResponseDto>> getSchedule(
            @PathVariable Long authorId,
            @PathVariable Long scheduleId
    ){
        ScheduleDetailResponseDto responseDto = scheduleService.getSchedule(authorId, scheduleId);
        return ResponseEntity.status(SuccessCode.SCHEDULE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode.SCHEDULE_SUCCESS, responseDto));
    }

    // 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ApiResponseDto<ScheduleResponseDto>> changeSchedule(@PathVariable Long authorId, @PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto){
        ScheduleResponseDto responseDto = scheduleService.changeSchedule(authorId, scheduleId, requestDto);
        return ResponseEntity.status(SuccessCode.SCHEDULE_UPDATE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode. SCHEDULE_UPDATE_SUCCESS, responseDto));
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ApiResponseDto<Void>> removeSchedule(@PathVariable Long authorId, @PathVariable Long scheduleId) {
        scheduleService.removeSchedule(authorId, scheduleId);
        return ResponseEntity.status(SuccessCode.SCHEDULE_DELETE_SUCCESS.getHttpStatus())
                .body(ApiResponseDto.success(SuccessCode. SCHEDULE_DELETE_SUCCESS, null));
    }

}
