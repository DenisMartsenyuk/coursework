package ru.lab.coursework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @PostMapping("/connect") //Подключение к родителю
    public ResponseEntity connect() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/disconnect") //Отключение от родителя
    public ResponseEntity disconnect() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add-session") //Сохранить новую сессию чтения
    public ResponseEntity addSession() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-session") //Удалить сессию чтения
    public ResponseEntity deleteSession() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save-review") //Сохранить отчет о прочтении
    public ResponseEntity saveReview() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/generate-diary") //Сформировать дневник
    public ResponseEntity generateDiary() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-diary") //Удалить дневник
    public ResponseEntity deleteDiary() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
