package com.example.eaSpringDemo.feedback;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private Long userId;
    private Long answerId;
    private LocalDate createdDate;

    public Feedback(String comment, Long userId, Long answerId, LocalDate createdDate) {
        this.comment = comment;
        this.userId = userId;
        this.answerId = answerId;
        this.createdDate = createdDate;
    }
}
