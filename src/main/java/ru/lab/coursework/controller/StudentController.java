package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.service.StudentService;


@RestController
@RequestMapping(value = "/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/parents")
    public ResponseEntity<?> getParents(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(studentService.getParents(idRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти родителей!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-session") //Сохранить новую сессию чтения
    public ResponseEntity<?> saveSession(@RequestBody ReadingSessionSaveRequestDTO readingSessionSaveRequestDTO) {
        try {
            studentService.saveSession(readingSessionSaveRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось сохранить сессию!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete-session") //Удалить сессию чтения
    public ResponseEntity<?> deleteSession(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            studentService.deleteSession(idRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось удалить сессию!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-report") //Сохранить отчет о прочтении
    public ResponseEntity<?> saveReport(@RequestBody ReportSaveRequestDTO reportSaveRequestDTO) {
        try {
            studentService.saveReport(reportSaveRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось сохранить отчет!"), HttpStatus.BAD_REQUEST);
        }
    }
}
