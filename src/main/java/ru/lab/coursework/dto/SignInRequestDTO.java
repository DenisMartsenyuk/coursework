package ru.lab.coursework.dto;

import lombok.Data;

@Data
public class SignInRequestDTO {
    String login;
    String password;
}
