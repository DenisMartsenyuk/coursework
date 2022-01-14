package ru.lab.coursework.dto;

import lombok.Data;

@Data
public class SaveAuthorRequestDTO {
    Long id;// (автора, если редактируется, будет null, если создается)
    String name;
    String surname;
    String midddleName;
}
