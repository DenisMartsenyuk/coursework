package ru.lab.coursework.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rd_reading_session")
@Data
public class ReadingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "reading_task_id")
    private ReadingTask readingTask;

    @Column(name = "reading_start")
    private Timestamp reading_start;

    @Column(name = "reading_end")
    private Timestamp reading_end;

}
