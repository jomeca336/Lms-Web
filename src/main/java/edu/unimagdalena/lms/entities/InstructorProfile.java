package edu.unimagdalena.lms.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instructor_profile")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class InstructorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String phone;
    private String bio;

    @OneToOne(optional = false)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;


}
