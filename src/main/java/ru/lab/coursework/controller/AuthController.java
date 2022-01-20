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
    public ResponseEntity<?> register(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        try {
            return new ResponseEntity<>(authService.addNewAccount(signUpRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Пользватель с таким логином или почтой уже существует!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sign-in") //войти
    public ResponseEntity<?> login(@RequestBody SignInRequestDTO signInRequestDTO) {
        try {
            return new ResponseEntity<>(authService.getAccount(signInRequestDTO), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Неправильно введен логин или пароль!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/connect") //соединить родителя и ребенка
    public ResponseEntity<?> connect(@RequestBody ConnectionRequestDTO connectionRequestDTO) {
        try {
            authService.connect(connectionRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось подключить ребенка к родителю."), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/disconnect") //отсоеденить родителя и ребенка
    public ResponseEntity<?> disconnect(@RequestBody ConnectionRequestDTO connectionRequestDTO) {
        try {
            authService.disconnect(connectionRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось отключить ребенка от родителя."), HttpStatus.BAD_REQUEST);
        }
    }

}
