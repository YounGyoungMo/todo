package com.example.todo.repository;

import com.example.todo.common.exception.base.CustomException;
import com.example.todo.common.exception.enums.ErrorCode;
import com.example.todo.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

  Reply findByParentComment_id(Long commentId);
    Optional<Reply> findById(Long commentId);
    default Reply findByIdOrElseThrow(Long commentId) {
      return findById(commentId).orElseThrow(()-> new CustomException(ErrorCode.CANT_FIND_REPLY));
    }

}
