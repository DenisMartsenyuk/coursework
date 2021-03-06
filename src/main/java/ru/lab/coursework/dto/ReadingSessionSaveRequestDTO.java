package ru.lab.coursework.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class ReadingSessionSaveRequestDTO {
    Long studentId;
    Long readingTaskId;
    Date date;
    Time readingStart;
    Time readingEnd;
}
