package ru.lab.coursework.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReadingTaskDetailsResponseDTO {
    Long id;
    WritingResponseDTO writing;
    Boolean completed;
    Timestamp deadline;
}
