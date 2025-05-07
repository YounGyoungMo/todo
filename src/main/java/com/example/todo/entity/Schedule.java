package com.example.todo.entity;

import com.example.todo.dto.TodoDto.ScheduleResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends BaseTimeEntity {

    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Schedule(Long userId, String title, String contents) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }

    public static Schedule of(Long userId, String title, String contents) {
        return new Schedule(userId, title, contents);
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
