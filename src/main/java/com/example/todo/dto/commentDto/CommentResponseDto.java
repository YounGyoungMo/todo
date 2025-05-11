package com.example.todo.dto.commentDto;
import com.example.todo.dto.replyDto.ReplyResponseDto;
import com.example.todo.entity.Comment;
import com.example.todo.entity.Reply;
import com.example.todo.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private final Long authorId;
    private final Long scheduleId;
    private final Long commentUserId;
    private final Long id;
    private final String contents;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ReplyResponseDto reply;

    public static CommentResponseDto from(Schedule schedule, Comment comment) {
        Reply reply = comment.getReply();
        return new CommentResponseDto(
                schedule.getAuthorId(),
                schedule.getId(),
                comment.getCommentUserId(),
                comment.getId(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                reply == null ? null : ReplyResponseDto.from(schedule, reply)
                );
    }

}
