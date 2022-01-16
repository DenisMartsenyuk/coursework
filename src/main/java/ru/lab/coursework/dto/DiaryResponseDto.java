package ru.lab.coursework.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class DiaryResponseDto {
    Long id;
    String name;
    Timestamp creationDate;
    String link;
}
