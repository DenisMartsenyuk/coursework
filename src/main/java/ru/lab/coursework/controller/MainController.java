package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.service.MainService;

import java.util.List;

@RestController
@RequestMapping(value = "/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/diaries") //получить все сформированные дневники по id студента (получить все репорты а потом исходя из этого все дневники
    public ResponseEntity<List<DiaryResponseDto>> getDiaries(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getDiaries(idRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/report") //Получить отчет о прочтении
    public ResponseEntity<ReportResponseDTO> getReport(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getReport(idRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/actual-tasks") //Получение списка заданий
    public ResponseEntity<List<ReadingTaskResponseDTO>> getActualTasks(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getTasks(idRequestDTO, false), HttpStatus.OK);
    }

    @GetMapping("/completed-tasks") //Получение списка ЗАВЕРШЕННЫХ заданий
    public ResponseEntity<List<ReadingTaskResponseDTO>> getCompletedTasks(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getTasks(idRequestDTO, true), HttpStatus.OK);
    }

    @GetMapping("/reading-sessions") //Reading-sessions произведения
    public ResponseEntity<List<ReadingSessionResponseDTO>> getReadingSessions(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getReadingSessions(idRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/reading-task-details") //Reading-tasks с сессиями произведения
    public ResponseEntity<ReadingTaskDetailsResponseDTO> getReadingTaskDetails(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getReadingTaskDetails(idRequestDTO), HttpStatus.OK);
    }

}
