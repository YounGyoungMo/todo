package com.example.todo.repository;

import com.example.todo.common.exception.base.CustomException;
import com.example.todo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.common.exception.enums.ErrorCode;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findBySchedule_IdAndCommentUserIdAndId(Long scheduleId, Long commentUserId, Long commentId);

    default Comment findBySchedule_IdAndCommentUserIdAndIdOrElse(Long scheduleId, Long commentUserId, Long commentId) {
        return findBySchedule_IdAndCommentUserIdAndId(scheduleId, commentUserId, commentId).orElseThrow(()-> new CustomException(ErrorCode.CANT_FIND_COMMENT));
    }

    Optional<List<Comment>> findBySchedule_Id(Long scheduleId);

    default List<Comment> findBySchedule_IdOrElseThrow(Long scheduleId) {
        return findBySchedule_Id(scheduleId).orElseThrow(()-> new CustomException(ErrorCode.CANT_FIND_COMMENT));
    }

}
