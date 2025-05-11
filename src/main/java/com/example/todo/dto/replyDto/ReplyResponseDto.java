package com.example.todo.dto.replyDto;

import com.example.todo.entity.Reply;
import com.example.todo.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReplyResponseDto {

    private final Long authorId;
    private final Long scheduleId;
    private final Long replyUserId;
    private final Long id;
    private final String contents;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReplyResponseDto from(Schedule schedule, Reply reply) {

        return new ReplyResponseDto(
                schedule.getAuthorId(),
                schedule.getId(),
                reply.getReplyUserId(),
                reply.getId(),
                reply.getContents(),
                reply.getCreatedAt(),
                reply.getUpdatedAt()
        );
    }

}
