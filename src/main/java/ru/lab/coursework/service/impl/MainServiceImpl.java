package ru.lab.coursework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.model.Diary;
import ru.lab.coursework.model.ReadingSession;
import ru.lab.coursework.model.ReadingTask;
import ru.lab.coursework.model.Report;
import ru.lab.coursework.repository.*;
import ru.lab.coursework.service.MainService;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
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

    @Override
    public List<DiaryResponseDto> getDiaries(IdRequestDTO idRequestDTO) {
        List<Diary> diaries = diaryRepository.findDiariesByStudentId(idRequestDTO.getId());
        return diaries.stream().map(x -> DiaryResponseDto.builder().id(x.getId()).name(x.getName()).creationDate(x.getCreationDate()).link(diaryFileRepository.findByDiaryId(x.getId()).getPath()).build()).collect(Collectors.toList());
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
    public List<ReadingSessionResponseDTO> getReadingSessions(IdRequestDTO idRequestDTO) {
        List<ReadingSession> readingSessions = readingSessionRepository.findReadingSessionsByReadingTaskId(idRequestDTO.getId());
        return readingSessions.stream().map(x -> ReadingSessionResponseDTO.builder().id(x.getId()).date(x.getDate()).readingStart(x.getReadingStart()).readingEnd(x.getReadingEnd()).duration(Time.valueOf(LocalTime.MIDNIGHT.plus(Duration.between(x.getReadingStart().toLocalTime(), x.getReadingEnd().toLocalTime())))).build()).collect(Collectors.toList());
    }

    @Override
    public ReadingTaskDetailsResponseDTO getReadingTaskDetails(IdRequestDTO idRequestDTO) {
        ReadingTask readingTask = readingTaskRepository.findReadingTaskById(idRequestDTO.getId());
        ReadingTaskDetailsResponseDTO readingTaskDetailsResponseDTO = new ReadingTaskDetailsResponseDTO();
        List<ReadingSession> readingSessions = readingSessionRepository.findReadingSessionsByReadingTaskId(idRequestDTO.getId());
        readingTaskDetailsResponseDTO.setId(readingTask.getId());
        readingTaskDetailsResponseDTO.setName(readingTask.getWriting().getName());
        readingTaskDetailsResponseDTO.setAuthor(readingTask.getWriting().getAuthor().getName() + " " + readingTask.getWriting().getAuthor().getSurname() + " " + (readingTask.getWriting().getAuthor().getMiddleName() == null ? "" : readingTask.getWriting().getAuthor().getMiddleName()));
        readingTaskDetailsResponseDTO.setDeadline(readingTask.getDeadline());
        readingTaskDetailsResponseDTO.setSessions(readingSessions.stream().map(x -> ReadingSessionResponseDTO.builder().id(x.getId()).date(x.getDate()).readingStart(x.getReadingStart()).readingEnd(x.getReadingEnd()).duration(Time.valueOf(LocalTime.MIDNIGHT.plus(Duration.between(x.getReadingStart().toLocalTime(), x.getReadingEnd().toLocalTime())))).build()).collect(Collectors.toList()));
        return readingTaskDetailsResponseDTO;
    }
}
