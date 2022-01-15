package ru.lab.coursework.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReadingSessionSaveRequestDTO {
    Long studentId;
    Long readingTaskId;
    Timestamp readingStart;
    Timestamp readingEnd;
}
