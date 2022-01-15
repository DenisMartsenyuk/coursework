package ru.lab.coursework.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rd_report")
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "reading_task_id")
    private ReadingTask readingTask;

    @Column(name = "characters")
    private String characters;

    @Column(name = "plot")
    private String plot;

    @Column(name = "review")
    private String review;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "edit_date")
    private Timestamp editDate;
}
