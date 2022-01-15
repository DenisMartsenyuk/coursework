package ru.lab.coursework.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ReadingTaskResponseDTO {
    Long id;
    Long parentId;
    String name;
    String author;
    Timestamp deadline;
}
