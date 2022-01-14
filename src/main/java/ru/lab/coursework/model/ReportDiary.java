package ru.lab.coursework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rd_report_diary")
@Data
public class ReportDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;
}
