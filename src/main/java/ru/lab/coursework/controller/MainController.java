package ru.lab.coursework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lab.coursework.dto.IdRequestDTO;

@RestController
@RequestMapping(value = "/main")
public class MainController {

    @GetMapping("/diaries") //получить все сформированные дневники по id студента (получить все репорты а потом исходя из этого все дневники
    public ResponseEntity getDiaries() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/report") //Получить отчет о прочтении
    public ResponseEntity getReport() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/tasks") //Получение списка заданий
    public ResponseEntity getTasks(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/completed-tasks") //Получение списка ЗАВЕРШЕННЫХ заданий
    public ResponseEntity getTasksCompleted() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/actual-tasks") //Получение списка заданий постранично (в конце если успеешь)
    public ResponseEntity getTasksActual() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/statistic") //Статистика произведения (дневник)
    public ResponseEntity getStatistic() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
