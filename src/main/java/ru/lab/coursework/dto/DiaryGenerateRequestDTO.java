package ru.lab.coursework.dto;

import lombok.Data;

import java.util.List;

@Data
public class DiaryGenerateRequestDTO {
    Long studentId;
    String name;
    List<Long> readingTasksId;
}
