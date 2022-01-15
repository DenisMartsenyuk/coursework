package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lab.coursework.dto.IdRequestDTO;
import ru.lab.coursework.dto.ReadingSessionResponseDTO;
import ru.lab.coursework.dto.ReadingTaskResponseDTO;
import ru.lab.coursework.service.MainService;

import java.util.List;

@RestController
@RequestMapping(value = "/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/diaries") //получить все сформированные дневники по id студента (получить все репорты а потом исходя из этого все дневники
    public ResponseEntity getDiaries() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/report") //Получить отчет о прочтении
    public ResponseEntity getReport() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/actual-tasks") //Получение списка заданий
    public ResponseEntity<List<ReadingTaskResponseDTO>> getActualTasks(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getTasks(idRequestDTO, false), HttpStatus.OK);
    }

    @GetMapping("/completed-tasks") //Получение списка ЗАВЕРШЕННЫХ заданий
    public ResponseEntity<List<ReadingTaskResponseDTO>> getCompletedTasks(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getTasks(idRequestDTO, true), HttpStatus.OK);
    }

    @GetMapping("/statistic") //Статистика произведения (дневник)
    public ResponseEntity<List<ReadingSessionResponseDTO>> getStatistic(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getStatistic(idRequestDTO), HttpStatus.OK);
    }

}
