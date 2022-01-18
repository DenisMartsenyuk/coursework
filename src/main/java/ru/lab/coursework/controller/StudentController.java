package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.service.StudentService;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/parents")
    public ResponseEntity<List<ParentResponseDTO>> getParents(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity<>(studentService.getParents(idRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/save-session") //Сохранить новую сессию чтения
    public ResponseEntity saveSession(@RequestBody ReadingSessionSaveRequestDTO readingSessionSaveRequestDTO) {
        studentService.saveSession(readingSessionSaveRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-session") //Удалить сессию чтения
    public ResponseEntity deleteSession(@RequestBody IdRequestDTO idRequestDTO) {
        studentService.deleteSession(idRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save-report") //Сохранить отчет о прочтении
    public ResponseEntity saveReport(@RequestBody ReportSaveRequestDTO reportSaveRequestDTO) {
        studentService.saveReport(reportSaveRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
