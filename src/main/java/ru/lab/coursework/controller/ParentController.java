package ru.lab.coursework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parent")
public class ParentController {

    @GetMapping("/children") //Получить список детей
    public ResponseEntity getChildren() {
        return new ResponseEntity(HttpStatus.OK);
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
    public ResponseEntity deleteAuthor() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save-writing") //Сохранить произведение (Добавить либо обновить, если есть)
    public ResponseEntity saveWriting() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-writing") //Удалить произведение
    public ResponseEntity deleteWriting() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/save-task") //Сохранить задание (Добавить либо обновить, если есть)
    public ResponseEntity saveTask() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete-task") //Удалить задание
    public ResponseEntity deleteTask() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
