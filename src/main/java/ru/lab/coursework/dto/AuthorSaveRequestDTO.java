package ru.lab.coursework.dto;

import lombok.Data;

@Data
public class AuthorSaveRequestDTO {
    Long id;
    Long parentId;
    String name;
    String surname;
    String middleName;
}
