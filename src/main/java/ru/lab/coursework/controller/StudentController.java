package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab.coursework.dto.IdRequestDTO;
import ru.lab.coursework.dto.ReadingSessionSaveRequestDTO;
import ru.lab.coursework.dto.ReadingTaskSaveRequestDTO;
import ru.lab.coursework.dto.ReportSaveRequestDTO;
import ru.lab.coursework.service.StudentService;

@RestController
@RequestMapping(value = "/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

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
        studentService.saveReport(reportSaveRequestDTO); //todo еще не реализовано
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/generate-diary") //Сформировать дневник
    public ResponseEntity generateDiary() {
        //todo сделать
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-diary") //Удалить дневник
    public ResponseEntity deleteDiary(@RequestBody IdRequestDTO idRequestDTO) {
        studentService.deleteDiary(idRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
