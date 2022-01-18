package ru.lab.coursework.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParentResponseDTO {
    Long id;
    String name;
    String surname;
    String middleName;
}
