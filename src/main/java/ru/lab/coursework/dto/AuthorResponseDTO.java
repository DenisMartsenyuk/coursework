package ru.lab.coursework.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorResponseDTO {
    Long id;
    String name;
    String surname;
    String middleName;

}
