package com.example.todo.service.reply;

import com.example.todo.common.exception.base.CustomException;
import com.example.todo.common.exception.enums.ErrorCode;
import com.example.todo.dto.commentDto.CommentRequestDto;
import com.example.todo.dto.replyDto.ReplyResponseDto;
import com.example.todo.entity.Comment;
import com.example.todo.entity.Reply;
import com.example.todo.entity.Schedule;
import com.example.todo.repository.CommentRepository;
import com.example.todo.repository.ReplyRepository;
import com.example.todo.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public ReplyResponseDto createReply(Long scheduleId, Long replyUserId, Long commentId, CommentRequestDto requestDto) {

        Reply existingReply = replyRepository.findByParentComment_id(commentId);
        if (existingReply != null) {
            throw new CustomException(ErrorCode.ALREADY_EXIST_REPLY);
        }

        Comment parentComment = commentRepository.findBySchedule_IdAndCommentUserIdAndIdOrElse(scheduleId, replyUserId, commentId);
        Schedule schedule = scheduleRepository.findByIdOrElse(scheduleId);

        Reply reply = new Reply(replyUserId, requestDto.getContents(), schedule, parentComment);
        replyRepository.save(reply);

        return ReplyResponseDto.from(schedule, reply);
    }

    @Override
    public ReplyResponseDto changeReply(Long scheduleId, Long commentId, CommentRequestDto requestDto) {
        Reply reply = replyRepository.findByIdOrElseThrow(commentId);
        Schedule schedule = scheduleRepository.findByIdOrElse(scheduleId);
        reply.update(requestDto.getContents());
        return ReplyResponseDto.from(schedule,reply);
    }

    @Override
    public void removeReply(Long commentId) {
        Reply reply = replyRepository.findByIdOrElseThrow(commentId);

        Comment parentComment = reply.getParentComment();
        if (parentComment != null) {
            parentComment.setReply(null);
        }

        replyRepository.delete(reply);
    }


}
