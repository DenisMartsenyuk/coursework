package ru.lab.coursework.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReadingTaskSaveRequestDTO {
    Long id;
    Long parentId;
    Long studentId;
    Long writingId;
    Timestamp deadline;
    Boolean completed;
}
