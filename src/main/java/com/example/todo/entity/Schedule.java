package com.example.todo.entity;

import com.example.todo.dto.TodoDto.ScheduleResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends BaseTimeEntity {

    private Long authorId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    private Long commentCount = 0L;

    @OneToMany(mappedBy = "schedule")
    private List<Comment> commentList = new ArrayList<>();

    public Schedule(Long authorId, String title, String contents) {
        this.authorId = authorId;
        this.title = title;
        this.contents = contents;
    }

    public static Schedule of(Long authorId, String title, String contents) {
        return new Schedule(authorId, title, contents);
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void incrementCommentCount() {
        this.commentCount += 1;
    }

}
