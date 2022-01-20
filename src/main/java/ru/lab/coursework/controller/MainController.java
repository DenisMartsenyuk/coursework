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
import java.io.FileNotFoundException;

@RestController
@RequestMapping(value = "/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PostMapping("/diary")
    public ResponseEntity<?> getDiary (@RequestBody IdRequestDTO idRequestDTO) {
        try {
            String path = mainService.getDiary(idRequestDTO);
            return getInputStreamResponseEntity(path);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Файл дневника не найден!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/diaries") //получить все сформированные дневники по id студента (получить все репорты а потом исходя из этого все дневники
    public ResponseEntity<?> getDiaries(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(mainService.getDiaries(idRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти дневники!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/report") //Получить отчет о прочтении
    public ResponseEntity<?> getReport(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(mainService.getReport(idRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти отчет!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/actual-tasks") //Получение списка заданий
    public ResponseEntity<?> getActualTasks(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(mainService.getTasks(idRequestDTO, false), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти список заданий!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/completed-tasks") //Получение списка ЗАВЕРШЕННЫХ заданий
    public ResponseEntity<?> getCompletedTasks(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(mainService.getTasks(idRequestDTO, true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти список выполненых заданий!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reading-task-details") //Получение списка заданий
    public ResponseEntity<?> getReadingTaskDetails(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(mainService.getReadingTaskDetails(idRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти список заданий!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reading-task-sessions") //Reading-task по id с сессиями произведения
    public ResponseEntity<?> getReadingTaskSessions(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(mainService.getReadingTaskSessions(idRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти задание!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reading-sessions") //Reading-sessions произведения
    public ResponseEntity<?> getReadingSessions(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(mainService.getReadingSessions(idRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти список сессий!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/author") //автор по id с сессиями произведения
    public ResponseEntity<?> getAuthor(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(mainService.getAuthor(idRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти автора!"), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/writing") //writing по id с сессиями произведения
    public ResponseEntity<?> getWriting(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            return new ResponseEntity<>(mainService.getWriting(idRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось найти произведение!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/generate-diary") //Сформировать дневник
    public ResponseEntity<?> generateDiary(@RequestBody DiaryGenerateRequestDTO diaryGenerateRequestDTO) {
        String path;
        try {
            path = mainService.generateDiary(diaryGenerateRequestDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось сгенерировать дневник!"), HttpStatus.BAD_REQUEST);
        }
        try {
            return getInputStreamResponseEntity(path);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Файл дневника не найден!"), HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<InputStreamResource> getInputStreamResponseEntity(String path) throws FileNotFoundException {
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename=" + path);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.setContentLength(file.length());
        return new ResponseEntity<>(new InputStreamResource(new FileInputStream(file)), headers, HttpStatus.OK);
    }

    @PostMapping("/delete-diary") //Удалить дневник
    public ResponseEntity<?> deleteDiary(@RequestBody IdRequestDTO idRequestDTO) {
        try {
            mainService.deleteDiary(idRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponseDTO("Не удалось удалить дневник!"), HttpStatus.BAD_REQUEST);
        }
    }
}
