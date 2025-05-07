package com.example.todo.repository;

import com.example.todo.common.exception.base.CustomException;
import com.example.todo.common.exception.enums.ErrorCode;
import com.example.todo.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule> findByUserId(Long id);
    default List<Schedule> findByUserIdOrElse(Long id) {
        List<Schedule> schedules = findByUserId(id);
        if (schedules.isEmpty()) {
            throw new CustomException(ErrorCode.CANT_FIND_SCHEDULE);
        }
        return schedules;
    }

    Optional<Schedule> findByUserIdAndId(Long userId, Long scheduleId);

    default Schedule findByUserIdAndIdOrElse(Long userId, Long scheduleId) {
        return findByUserIdAndId(userId, scheduleId).orElseThrow( () -> new CustomException(ErrorCode.CANT_FIND_THIS_SCHEDULE));
    }





}
