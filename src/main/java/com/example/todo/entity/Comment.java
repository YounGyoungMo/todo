package com.example.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    @OneToOne(mappedBy = "parentComment",orphanRemoval = true, cascade = CascadeType.ALL)
    private Reply reply;

    public Comment(Long commentUserId, String content, Schedule schedule) {
        this.commentUserId = commentUserId;
        this.contents = content;
        this.schedule = schedule;
        schedule.incrementCommentCount();
    }

    public void update(String contents) {
        this.contents = contents;
    }

}
