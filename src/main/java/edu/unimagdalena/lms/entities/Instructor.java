package edu.unimagdalena.lms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "instructor")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Instructor {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String email;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne(mappedBy = "instructor")
    private InstructorProfile profile;

    @OneToMany(mappedBy = "instructor")
    private Set<Course> courses;
}
