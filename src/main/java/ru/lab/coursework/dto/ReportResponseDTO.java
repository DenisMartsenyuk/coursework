package ru.lab.coursework.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReportResponseDTO {
    String characters;
    String plot;
    String review;
    Timestamp creationDate;
    Timestamp editDate;
}
