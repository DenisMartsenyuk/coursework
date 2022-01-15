package ru.lab.coursework.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
public class ReadingSessionResponseDTO {
    Long id;
    Date date;
    Time readingStart;
    Time readingEnd;
    Time duration;
}
