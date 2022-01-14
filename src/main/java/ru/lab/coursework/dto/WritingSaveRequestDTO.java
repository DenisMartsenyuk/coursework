package ru.lab.coursework.dto;

import lombok.Data;

@Data
public class WritingSaveRequestDTO {
    Long id;
    Long parentId;
    Long authorId;
    String name;
}
