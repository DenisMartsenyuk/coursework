package ru.lab.coursework.service;

import ru.lab.coursework.dto.*;

public interface AuthService {
    SignUpResponseDTO addNewAccount(SignUpRequestDTO signUpRequestDTO);
    SignInResponseDTO getAccount(SignInRequestDTO signInRequestDTO);
    void connect(ConnectionRequestDTO connectionRequestDTO);
    void disconnect(ConnectionRequestDTO connectionRequestDTO);
    void setDisableAccount(String login);
}
