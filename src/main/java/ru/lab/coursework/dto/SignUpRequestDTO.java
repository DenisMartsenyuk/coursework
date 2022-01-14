package ru.lab.coursework.dto;

import lombok.Data;

@Data
public class SignUpRequestDTO {
    String login;
    String password;
    String name;
    String surname;
    String middleName;
    String email;
    String roleName;
}
