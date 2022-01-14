package ru.lab.coursework.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rd_reading_task")
@Data
public class ReadingTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "writing_id")
    private Writing writing;

    @Column(name = "deadline")
    private Timestamp deadline; //todo мб нет

    @Column(name = "completed")
    private Boolean completed;
}
