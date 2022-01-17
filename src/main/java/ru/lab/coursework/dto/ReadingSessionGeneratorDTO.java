package ru.lab.coursework.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReadingSessionGeneratorDTO {
    String date;
    String readingStart;
    String readingEnd;
    String duration;
}
