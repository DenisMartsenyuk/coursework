package ru.lab.coursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab.coursework.model.User;
import ru.lab.coursework.service.UserService;

@RestController
@RequestMapping(value = "/account")
public class AuthController {

    private final UserService userService;

    @Autowired
    AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up") //регистрация
    public ResponseEntity register() {
        userService.addNewAccount();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/sign-in") //войти
    public ResponseEntity<User> login() {
        return new ResponseEntity<>(userService.getAccount("e", "1"), HttpStatus.OK);
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
