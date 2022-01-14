package ru.lab.coursework.dto;

import lombok.Data;

@Data
public class SignInResponseDTO {
    Long id;
    String name;
    String surname;
    String middleName;
    String email;
    String role;
}
