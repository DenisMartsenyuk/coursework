package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab.coursework.dto.AuthorResponseDTO;
import ru.lab.coursework.dto.ChildResponseDTO;
import ru.lab.coursework.dto.IdRequestDTO;
import ru.lab.coursework.dto.WritingResponseDto;
import ru.lab.coursework.service.ParentService;

import java.util.List;

@RestController
@RequestMapping(value = "/parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @GetMapping("/children") //Получить список детей
    public ResponseEntity<List<ChildResponseDTO>> getChildren(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity(parentService.getChildren(idRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/authors") //Получить список авторов
    public ResponseEntity<List<AuthorResponseDTO>> getAuthors(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity(parentService.getAuthors(idRequestDTO),HttpStatus.OK);
    }

    @GetMapping("/writings") //Получить список произведений
    public ResponseEntity<List<WritingResponseDto>> getWritings(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity(parentService.getWritings(idRequestDTO),HttpStatus.OK);
    }

    @PostMapping("/delete-child") //Удалить ребенка
    public ResponseEntity deleteChild() {
        //todo сделать потом
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save-author") //Сохранить автора произведения (Добавить либо обновить, если есть)
    public ResponseEntity saveAuthor() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-author") //Удалить автора произведения
    public ResponseEntity deleteAuthor(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save-writing") //Сохранить произведение (Добавить либо обновить, если есть)
    public ResponseEntity saveWriting() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-writing") //Удалить произведение
    public ResponseEntity deleteWriting(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save-task") //Сохранить задание (Добавить либо обновить, если есть)
    public ResponseEntity saveTask() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-task") //Удалить задание
    public ResponseEntity deleteTask(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
