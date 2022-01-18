package ru.lab.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.service.MainService;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PostMapping("/diary")
    public ResponseEntity<InputStreamResource> getDiary (@RequestBody IdRequestDTO idRequestDTO) {
        String path = mainService.getDiary(idRequestDTO);
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        headers.add("Access-Control-Allow-Origin", "*");
//        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename=" + path);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.setContentLength(file.length());

        try {
            return new ResponseEntity<>(new InputStreamResource(new FileInputStream(file)), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

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

    @PostMapping("/generate-diary") //Сформировать дневник
    public ResponseEntity<InputStreamResource> generateDiary(@RequestBody DiaryGenerateRequestDTO diaryGenerateRequestDTO) {
        //todo добавить генерацию дневника
        String path = mainService.generateDiary(diaryGenerateRequestDTO);
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        headers.add("Access-Control-Allow-Origin", "*");
//        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
//        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename=" + path);
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
        headers.setContentLength(file.length());

        try {
            return new ResponseEntity<>(new InputStreamResource(new FileInputStream(file)), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete-diary") //Удалить дневник
    public ResponseEntity deleteDiary(@RequestBody IdRequestDTO idRequestDTO) {
        mainService.deleteDiary(idRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
