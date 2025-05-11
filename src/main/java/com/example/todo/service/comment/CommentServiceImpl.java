package com.example.todo.service.comment;
import com.example.todo.dto.commentDto.CommentRequestDto;
import com.example.todo.dto.commentDto.CommentResponseDto;
import com.example.todo.entity.Comment;
import com.example.todo.entity.Reply;
import com.example.todo.entity.Schedule;
import com.example.todo.repository.CommentRepository;
import com.example.todo.repository.ReplyRepository;
import com.example.todo.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public CommentResponseDto createComment(Long authorId,Long scheduleId, Long commentUserId,CommentRequestDto commentRequestDto) {

        Schedule schedule = scheduleRepository.findByAuthorIdAndIdOrElse(authorId, scheduleId);
        Comment comment = new Comment(commentUserId, commentRequestDto.getContents(), schedule);
        commentRepository.save(comment);

        return CommentResponseDto.from(schedule, comment);
    }

    @Transactional (readOnly = true)
    public CommentResponseDto getComment(Long scheduleId, Long commentUserId, Long commentId) {
        Schedule schedule = scheduleRepository.findByIdOrElse(scheduleId);
        Comment comment = commentRepository.findBySchedule_IdAndCommentUserIdAndIdOrElse(scheduleId, commentUserId, commentId);
        return CommentResponseDto.from(schedule, comment);
    }

    @Override
    public CommentResponseDto changeComment(Long scheduleId, Long commentUserId, Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findBySchedule_IdAndCommentUserIdAndIdOrElse(scheduleId, commentUserId, commentId);
        Schedule schedule = scheduleRepository.findByIdOrElse(scheduleId);
        comment.update(requestDto.getContents());
        return CommentResponseDto.from(schedule, comment);
    }

    @Override
    public void removeComment(Long scheduleId, Long commentUserId, Long commentId) {
        Comment comment = commentRepository.findBySchedule_IdAndCommentUserIdAndIdOrElse(scheduleId,commentUserId,commentId);
        Reply reply = replyRepository.findByParentComment_id(commentId);
        if (reply != null) {
            replyRepository.delete(reply);
        }
        commentRepository.delete(comment);
    }



}
