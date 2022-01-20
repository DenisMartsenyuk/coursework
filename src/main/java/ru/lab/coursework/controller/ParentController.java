package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.service.ParentService;

@RestController
@RequestMapping(value = "/parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @PostMapping("/child") //Получить ребенка
    public ResponseEntity<?> getChild(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(parentService.getChild(idRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти ребенка!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/children") //Получить список детей
    public ResponseEntity<?> getChildren(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(parentService.getChildren(idRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти список детей!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authors") //Получить список авторов
    public ResponseEntity<?> getAuthors(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(parentService.getAuthors(idRequestDTO),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти список авторов!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/writings") //Получить список произведений
    public ResponseEntity<?> getWritings(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(parentService.getWritings(idRequestDTO),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти список произведений!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-author") //Сохранить автора произведения (Добавить либо обновить, если есть)
    public ResponseEntity<?> saveAuthor(@RequestBody AuthorSaveRequestDTO authorSaveRequestDTO) {
        try {
            parentService.saveAuthor(authorSaveRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось сохранить автора!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete-author") //Удалить автора произведения
    public ResponseEntity<?> deleteAuthor(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            parentService.deleteAuthor(idRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось удалить автора!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-writing") //Сохранить произведение (Добавить либо обновить, если есть)
    public ResponseEntity<?> saveWriting(@RequestBody WritingSaveRequestDTO writingSaveRequestDTO) {
        try {
            parentService.saveWriting(writingSaveRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось сохранить произведение!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete-writing") //Удалить произведение
    public ResponseEntity<?> deleteWriting(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            parentService.deleteWriting(idRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось удалить произведение!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-task") //Сохранить задание (Добавить либо обновить, если есть)
    public ResponseEntity<?> saveTask(@RequestBody ReadingTaskSaveRequestDTO readingTaskSaveRequestDTO) {
        try {
            parentService.saveTask(readingTaskSaveRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось сохранить задание!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete-task") //Удалить задание
    public ResponseEntity<?> deleteTask(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            parentService.deleteTask(idRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось удалить задание!"), HttpStatus.BAD_REQUEST);
        }
    }
}
