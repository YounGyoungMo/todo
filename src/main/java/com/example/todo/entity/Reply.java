package com.example.todo.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "reply")
@NoArgsConstructor

public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long replyUserId;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @OneToOne
    @JoinColumn(name = "comment_id")
    private Comment parentComment;

    public Reply(Long replyUserId, String contents, Schedule schedule, Comment parentComment) {
        this.replyUserId = replyUserId;
        this.contents = contents;
        this.schedule = schedule;
        this.parentComment = parentComment;
        schedule.incrementCommentCount();
    }

    public void update(String contents) {
        this.contents = contents;
    }
}
