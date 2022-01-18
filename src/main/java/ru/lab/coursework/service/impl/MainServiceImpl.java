package ru.lab.coursework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.lab.coursework.component.DiaryGenerator;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.model.*;
import ru.lab.coursework.repository.*;
import ru.lab.coursework.service.MainService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final ReadingTaskRepository readingTaskRepository;
    private final ReadingSessionRepository readingSessionRepository;
    private final ReportRepository reportRepository;
    private final DiaryRepository diaryRepository;
    private final DiaryFileRepository diaryFileRepository;
    private final AuthorRepository authorRepository;
    private final WritingRepository writingRepository;
    private final DiaryGenerator diaryGenerator;
    private final ReportDiaryRepository reportDiaryRepository;
    private final UserRepository userRepository;

    @Override
    public String getDiary(IdRequestDTO idRequestDTO) {
        return diaryFileRepository.findByDiaryId(idRequestDTO.getId()).getPath();
    }

    @Override
    public List<DiaryResponseDto> getDiaries(IdRequestDTO idRequestDTO) {
        List<Diary> diaries = diaryRepository.findDiariesByStudentId(idRequestDTO.getId());
        return diaries.stream().map(x -> DiaryResponseDto.builder().id(x.getId()).name(x.getName()).creationDate(x.getCreationDate()).build()).collect(Collectors.toList());
    }

    @Override
    public ReportResponseDTO getReport(IdRequestDTO idRequestDTO) {
        Report report = reportRepository.findReportByReadingTaskId(idRequestDTO.getId());
        ReportResponseDTO reportResponseDTO = new ReportResponseDTO();
        reportResponseDTO.setCharacters(report.getCharacters());
        reportResponseDTO.setPlot(report.getPlot());
        reportResponseDTO.setReview(report.getReview());
        reportResponseDTO.setCreationDate(report.getCreationDate());
        reportResponseDTO.setEditDate(report.getEditDate());
        return reportResponseDTO;
    }

    @Override
    public List<ReadingTaskResponseDTO> getTasks(IdRequestDTO idRequestDTO, Boolean completed) {
        List<ReadingTask> readingTasks = readingTaskRepository.findReadingTasksByStudentIdAndCompleted(idRequestDTO.getId(), completed);
        return readingTasks.stream().map(x -> ReadingTaskResponseDTO.builder().id(x.getId()).parentId(x.getParent().getId()).name(x.getWriting().getName()).author(x.getWriting().getAuthor().getName() + " " + x.getWriting().getAuthor().getSurname() + " " + (x.getWriting().getAuthor().getMiddleName() == null ? "" : x.getWriting().getAuthor().getMiddleName())).deadline(x.getDeadline()).build()).collect(Collectors.toList());
    }

    @Override
    public ReadingTaskDetailsResponseDTO getReadingTaskDetails(IdRequestDTO idRequestDTO) {
        ReadingTask readingTask = readingTaskRepository.findReadingTaskById(idRequestDTO.getId());
        Writing writing = writingRepository.findWritingById(readingTask.getWriting().getId());
        ReadingTaskDetailsResponseDTO readingTaskDetailsResponseDTO = new ReadingTaskDetailsResponseDTO();
        readingTaskDetailsResponseDTO.setId(readingTask.getId());
        readingTaskDetailsResponseDTO.setWriting(WritingResponseDTO.builder().id(writing.getId()).name(writing.getName()).author(AuthorResponseDTO.builder().id(writing.getAuthor().getId()).name(writing.getAuthor().getName()).surname(writing.getAuthor().getSurname()).middleName(writing.getAuthor().getMiddleName()).build()).build());
        readingTaskDetailsResponseDTO.setCompleted(readingTask.getCompleted());
        readingTaskDetailsResponseDTO.setDeadline(readingTask.getDeadline());
        return readingTaskDetailsResponseDTO;
    }

    @Override
    public ReadingTaskSessionsResponseDTO getReadingTaskSessions(IdRequestDTO idRequestDTO) {
        ReadingTask readingTask = readingTaskRepository.findReadingTaskById(idRequestDTO.getId());
        ReadingTaskSessionsResponseDTO readingTaskSessionsResponseDTO = new ReadingTaskSessionsResponseDTO();
        List<ReadingSession> readingSessions = readingSessionRepository.findReadingSessionsByReadingTaskId(idRequestDTO.getId());
        readingTaskSessionsResponseDTO.setId(readingTask.getId());
        readingTaskSessionsResponseDTO.setName(readingTask.getWriting().getName());
        readingTaskSessionsResponseDTO.setAuthor(readingTask.getWriting().getAuthor().getName() + " " + readingTask.getWriting().getAuthor().getSurname() + " " + (readingTask.getWriting().getAuthor().getMiddleName() == null ? "" : readingTask.getWriting().getAuthor().getMiddleName()));
        readingTaskSessionsResponseDTO.setDeadline(readingTask.getDeadline());
        readingTaskSessionsResponseDTO.setSessions(readingSessions.stream().map(x -> ReadingSessionResponseDTO.builder().id(x.getId()).date(x.getDate()).readingStart(x.getReadingStart()).readingEnd(x.getReadingEnd()).duration(Time.valueOf(LocalTime.MIDNIGHT.plus(Duration.between(x.getReadingStart().toLocalTime(), x.getReadingEnd().toLocalTime())))).build()).collect(Collectors.toList()));
        return readingTaskSessionsResponseDTO;
    }

    @Override
    public List<ReadingSessionResponseDTO> getReadingSessions(IdRequestDTO idRequestDTO) {
        List<ReadingSession> readingSessions = readingSessionRepository.findReadingSessionsByReadingTaskId(idRequestDTO.getId());
        return readingSessions.stream().map(x -> ReadingSessionResponseDTO.builder().id(x.getId()).date(x.getDate()).readingStart(x.getReadingStart()).readingEnd(x.getReadingEnd()).duration(Time.valueOf(LocalTime.MIDNIGHT.plus(Duration.between(x.getReadingStart().toLocalTime(), x.getReadingEnd().toLocalTime())))).build()).collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDTO getAuthor(IdRequestDTO idRequestDTO) {
        Author author = authorRepository.findAuthorById(idRequestDTO.getId());
        return AuthorResponseDTO.builder().id(author.getId()).name(author.getName()).surname(author.getSurname()).middleName(author.getMiddleName()).build();
    }

    @Override
    public WritingResponseDTO getWriting(IdRequestDTO idRequestDTO) {
        Writing writing = writingRepository.findWritingById(idRequestDTO.getId());
        return WritingResponseDTO.builder().id(writing.getId()).name(writing.getName()).author(AuthorResponseDTO.builder().id(writing.getAuthor().getId()).name(writing.getAuthor().getName()).surname(writing.getAuthor().getSurname()).middleName(writing.getAuthor().getMiddleName()).build()).build();
    }

    @Override
    public String generateDiary(DiaryGenerateRequestDTO diaryGenerateRequestDTO) {
        List<Report> reports = diaryGenerateRequestDTO.getReadingTasksId().stream().map(reportRepository::findReportByReadingTaskId).collect(Collectors.toList());

        List<ReportGeneratorDTO> reportsGeneratorList = new ArrayList<>();
        for (int i = 0; i < reports.size(); ++i) {
            Report report = reports.get(i);
            ReportGeneratorDTO reportGenerator = new ReportGeneratorDTO();
            reportGenerator.setName(report.getReadingTask().getWriting().getName());
            Author author = report.getReadingTask().getWriting().getAuthor();
            reportGenerator.setAuthor(author.getName() + " " + author.getSurname() + " " + (author.getMiddleName() == null ? "" : author.getMiddleName()));
            List<ReadingSession> readingSessions = readingSessionRepository.findReadingSessionsByReadingTaskIdOrderByDate(report.getReadingTask().getId());
            reportGenerator.setDateStart(readingSessions.get(0).getDate().toString());
            reportGenerator.setDateEnd(readingSessions.get(readingSessions.size() - 1).getDate().toString());
            reportGenerator.setCharacters(report.getCharacters());
            reportGenerator.setPlot(report.getPlot());
            reportGenerator.setReview(report.getReview());
            reportGenerator.setReadingSessions(readingSessions.stream().map(x -> ReadingSessionGeneratorDTO.builder().date(x.getDate().toString()).readingStart(x.getReadingStart().toString()).readingEnd(x.getReadingEnd().toString()).duration(Time.valueOf(LocalTime.MIDNIGHT.plus(Duration.between(x.getReadingStart().toLocalTime(), x.getReadingEnd().toLocalTime()))).toString()).build()).collect(Collectors.toList()));
            reportsGeneratorList.add(reportGenerator);
        }

        User student = userRepository.findUserById(diaryGenerateRequestDTO.getStudentId());
        diaryGenerator.setName(diaryGenerateRequestDTO.getName());
        diaryGenerator.setStudentName(student.getName() + " " + student.getSurname() + " " + (student.getMiddleName() == null ? "" : student.getMiddleName()));
        diaryGenerator.setSetDate(new Date(System.currentTimeMillis()).toString());
        diaryGenerator.setReports(reportsGeneratorList);
        try {
            String path = diaryGenerator.generateDiary();
            Diary diary = new Diary();
            diary.setName(diaryGenerateRequestDTO.getName());
            diary.setCreationDate(new Timestamp(System.currentTimeMillis()));
            diary.setStudent(userRepository.findUserById(diaryGenerateRequestDTO.getStudentId()));
            diary = diaryRepository.save(diary);
            DiaryFile diaryFile = new DiaryFile();
            diaryFile.setDiary(diary);
            diaryFile.setCreationDate(diary.getCreationDate());
            diaryFile.setPath(path);
            diaryFileRepository.save(diaryFile);
            for (Report report : reports) {
                ReportDiary reportDiary = new ReportDiary();
                reportDiary.setReport(report);
                reportDiary.setDiary(diary);
                reportDiaryRepository.save(reportDiary);
            }
            return path;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteDiary(IdRequestDTO idRequestDTO) {
        DiaryFile diaryFile = diaryFileRepository.findByDiaryId(idRequestDTO.getId());
        File file = new File(diaryFile.getPath());
        file.delete();
        diaryRepository.deleteById(idRequestDTO.getId());
    }
}
