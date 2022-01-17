package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.service.MainService;

import java.util.List;

@RestController
@RequestMapping(value = "/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PostMapping("/diaries") //получить все сформированные дневники по id студента (получить все репорты а потом исходя из этого все дневники
    public ResponseEntity<List<DiaryResponseDto>> getDiaries(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getDiaries(idRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/report") //Получить отчет о прочтении
    public ResponseEntity<ReportResponseDTO> getReport(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getReport(idRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/actual-tasks") //Получение списка заданий
    public ResponseEntity<List<ReadingTaskResponseDTO>> getActualTasks(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getTasks(idRequestDTO, false), HttpStatus.OK);
    }

    @PostMapping("/completed-tasks") //Получение списка ЗАВЕРШЕННЫХ заданий
    public ResponseEntity<List<ReadingTaskResponseDTO>> getCompletedTasks(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getTasks(idRequestDTO, true), HttpStatus.OK);
    }

    @PostMapping("/reading-task-details") //Получение списка заданий
    public ResponseEntity<ReadingTaskDetailsResponseDTO> getReadingTaskDetails(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getReadingTaskDetails(idRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/reading-task-sessions") //Reading-task по id с сессиями произведения
    public ResponseEntity<ReadingTaskSessionsResponseDTO> getReadingTaskSessions(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getReadingTaskSessions(idRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/reading-sessions") //Reading-sessions произведения
    public ResponseEntity<List<ReadingSessionResponseDTO>> getReadingSessions(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getReadingSessions(idRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/author") //автор по id с сессиями произведения
    public ResponseEntity<AuthorResponseDTO> getAuthor(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getAuthor(idRequestDTO), HttpStatus.OK);
    }


    @PostMapping("/writing") //writing по id с сессиями произведения
    public ResponseEntity<WritingResponseDTO> getWriting(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(mainService.getWriting(idRequestDTO), HttpStatus.OK);
    }
}
