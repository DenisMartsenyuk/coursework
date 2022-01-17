package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab.coursework.dto.*;
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

    @PostMapping("/sign-in") //войти
    public ResponseEntity<SignInResponseDTO> login(@RequestBody SignInRequestDTO signInRequestDTO) {
        return new ResponseEntity<>(authService.getAccount(signInRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/sign-out") //выйти
    public ResponseEntity logout() {
        //todo потом надо сделать
        return new ResponseEntity(HttpStatus.OK); //сбросить токен
    }

    @PostMapping("/connect") //соединить родителя и ребенка
    public ResponseEntity connect(@RequestBody ConnectionRequestDTO connectionRequestDTO) {
        authService.connect(connectionRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/disconnect") //отсоеденить родителя и ребенка
    public ResponseEntity disconnect(@RequestBody ConnectionRequestDTO connectionRequestDTO) {
        authService.disconnect(connectionRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/disable") //сделать неактивным
    public ResponseEntity setDisabled() {
        //todo потом надо сделать
        return new ResponseEntity(HttpStatus.OK);
    }
}
