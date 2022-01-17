package ru.lab.coursework.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ReadingTaskSessionsResponseDTO {
    Long id;
    String name;
    String author;
    Timestamp deadline;
    List<ReadingSessionResponseDTO> sessions;
}
