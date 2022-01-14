package ru.lab.coursework.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rd_diary_file")
@Data
public class DiaryFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @Column(name = "path")
    private String path;

    @Column(name = "creation_date")
    private Timestamp creation_date;
}
