package ru.lab.coursework.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DiaryResponseDto {
    Long id;
    String name;
    Timestamp creationDate;
}
