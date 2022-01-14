package ru.lab.coursework.service;

import ru.lab.coursework.dto.SignInRequestDTO;
import ru.lab.coursework.dto.SignInResponseDTO;
import ru.lab.coursework.dto.SignUpRequestDTO;
import ru.lab.coursework.dto.SignUpResponseDTO;

public interface AuthService {
    SignUpResponseDTO addNewAccount(SignUpRequestDTO signUpRequestDTO);
    SignInResponseDTO getAccount(SignInRequestDTO signInRequestDTO);
    void setDisableAccount(String login);
}
