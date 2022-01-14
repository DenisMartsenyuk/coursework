package ru.lab.coursework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/main")
public class MainController {

    @GetMapping("/diaries") //получить все сформированные дневники
    public ResponseEntity getDiaries() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/review") //Получить отчет о прочтении
    public ResponseEntity getReview() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/tasks") //Получение списка заданий
    public ResponseEntity getTasks() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/tasks/completed") //Получение списка ЗАВЕРШЕННЫХ заданий todo мб не будет работать
    public ResponseEntity getTasksCompleted() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/tasks/actual/") //Получение списка заданий постранично (в конце если успеешь) todo мб не будет работать
    public ResponseEntity getTasksActual() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/statistic") //Статистика произведения (дневник)
    public ResponseEntity getStatistic() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
