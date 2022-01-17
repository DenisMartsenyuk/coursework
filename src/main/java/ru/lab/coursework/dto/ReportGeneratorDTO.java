package ru.lab.coursework.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportGeneratorDTO {
    String name;
    String author;
    String dateStart;
    String dateEnd;
    String characters;
    String plot;
    String review;
    List<ReadingSessionGeneratorDTO> readingSessions;
}
