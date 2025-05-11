package com.example.todo.repository;

import com.example.todo.common.exception.base.CustomException;
import com.example.todo.common.exception.enums.ErrorCode;
import com.example.todo.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule> findByAuthorId(Long id);
    default List<Schedule> findByAuthorIdOrElse(Long id) {
        List<Schedule> schedules = findByAuthorId(id);
        if (schedules.isEmpty()) {
            throw new CustomException(ErrorCode.CANT_FIND_SCHEDULE);
        }
        return schedules;
    }

    Optional<Schedule> findByAuthorIdAndId(Long authorId, Long scheduleId);

    default Schedule findByAuthorIdAndIdOrElse(Long authorId, Long scheduleId) {
        return findByAuthorIdAndId(authorId, scheduleId).orElseThrow( () -> new CustomException(ErrorCode.CANT_FIND_THIS_SCHEDULE));
    }

    Optional<Schedule> findById(Long id);

    default Schedule findByIdOrElse(Long id) {
        return findById(id).orElseThrow(()-> new CustomException(ErrorCode.CANT_FIND_THIS_SCHEDULE));
    }

}
