package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.service.ParentService;

import java.util.List;

@RestController
@RequestMapping(value = "/parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @PostMapping("/child") //Получить список детей
    public ResponseEntity<ChildResponseDTO> getChild(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity(parentService.getChild(idRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/children") //Получить список детей
    public ResponseEntity<List<ChildResponseDTO>> getChildren(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity(parentService.getChildren(idRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/authors") //Получить список авторов
    public ResponseEntity<List<AuthorResponseDTO>> getAuthors(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity(parentService.getAuthors(idRequestDTO),HttpStatus.OK);
    }

    @PostMapping("/writings") //Получить список произведений
    public ResponseEntity<List<WritingResponseDTO>> getWritings(@RequestBody IdRequestDTO idRequestDTO) {
        return new ResponseEntity(parentService.getWritings(idRequestDTO),HttpStatus.OK);
    }

    @PostMapping("/save-author") //Сохранить автора произведения (Добавить либо обновить, если есть)
    public ResponseEntity saveAuthor(@RequestBody AuthorSaveRequestDTO authorSaveRequestDTO) {
        parentService.saveAuthor(authorSaveRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-author") //Удалить автора произведения
    public ResponseEntity deleteAuthor(@RequestBody IdRequestDTO idRequestDTO) {
        parentService.deleteAuthor(idRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save-writing") //Сохранить произведение (Добавить либо обновить, если есть)
    public ResponseEntity saveWriting(@RequestBody WritingSaveRequestDTO writingSaveRequestDTO) {
        parentService.saveWriting(writingSaveRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-writing") //Удалить произведение
    public ResponseEntity deleteWriting(@RequestBody IdRequestDTO idRequestDTO) {
        parentService.deleteWriting(idRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save-task") //Сохранить задание (Добавить либо обновить, если есть)
    public ResponseEntity saveTask(@RequestBody ReadingTaskSaveRequestDTO readingTaskSaveRequestDTO) {
        parentService.saveTask(readingTaskSaveRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-task") //Удалить задание
    public ResponseEntity deleteTask(@RequestBody IdRequestDTO idRequestDTO) {
        parentService.deleteTask(idRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
