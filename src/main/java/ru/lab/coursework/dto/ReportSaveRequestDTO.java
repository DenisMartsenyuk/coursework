package ru.lab.coursework.dto;

import lombok.Data;

@Data
public class ReportSaveRequestDTO {
    Long readingTaskId;
    String characters;
    String plot;
    String review;
}
