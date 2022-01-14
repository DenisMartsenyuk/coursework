package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab.coursework.dto.SignInRequestDTO;
import ru.lab.coursework.dto.SignInResponseDTO;
import ru.lab.coursework.dto.SignUpRequestDTO;
import ru.lab.coursework.dto.SignUpResponseDTO;
import ru.lab.coursework.service.AuthService;

@RestController
@RequestMapping(value = "/account")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up") //регистрация
    public ResponseEntity<SignUpResponseDTO> register(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        return new ResponseEntity<>(authService.addNewAccount(signUpRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/sign-in") //войти
    public ResponseEntity<SignInResponseDTO> login(@RequestBody SignInRequestDTO signInRequestDTO) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sign-out") //выйти
    public ResponseEntity logout() {
        return new ResponseEntity(HttpStatus.OK); //сбросить токен
    }

    @PostMapping("/disable") //сделать неактивным
    public ResponseEntity setDisabled() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
