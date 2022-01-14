package ru.lab.coursework.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WritingResponseDto {
    Long id;
    String name;
    AuthorResponseDTO author;
}
