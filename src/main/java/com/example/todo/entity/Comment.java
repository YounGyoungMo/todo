package com.example.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long commentUserId;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(Long commentUserId, String content, Schedule schedule) {
        this.commentUserId = commentUserId;
        this.contents = content;
        this.schedule = schedule;
    }

    public void update(String contents) {
        this.contents = contents;
    }
}
