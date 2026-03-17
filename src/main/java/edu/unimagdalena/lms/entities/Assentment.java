package edu.unimagdalena.lms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "assentment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Assentment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String type;
    private int score;

    @Column(name = "taken_at")
    private Instant takenAt;


}
