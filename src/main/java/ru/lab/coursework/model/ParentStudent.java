package ru.lab.coursework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rd_parent_student")
@Data
public class ParentStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;
}
