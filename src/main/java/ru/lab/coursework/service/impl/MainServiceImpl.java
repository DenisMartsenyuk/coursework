package ru.lab.coursework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lab.coursework.dto.IdRequestDTO;
import ru.lab.coursework.dto.ReadingSessionResponseDTO;
import ru.lab.coursework.dto.ReadingTaskResponseDTO;
import ru.lab.coursework.dto.ReportResponseDTO;
import ru.lab.coursework.model.ReadingSession;
import ru.lab.coursework.model.ReadingTask;
import ru.lab.coursework.model.Report;
import ru.lab.coursework.repository.ReadingSessionRepository;
import ru.lab.coursework.repository.ReadingTaskRepository;
import ru.lab.coursework.repository.ReportRepository;
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
    public List<ReadingSessionResponseDTO> getStatistic(IdRequestDTO idRequestDTO) {
        List<ReadingSession> readingSessions = readingSessionRepository.findReadingSessionsByReadingTaskId(idRequestDTO.getId());
        return readingSessions.stream().map(x -> ReadingSessionResponseDTO.builder().id(x.getId()).date(x.getDate()).readingStart(x.getReadingStart()).readingEnd(x.getReadingEnd()).duration(Time.valueOf(LocalTime.MIDNIGHT.plus(Duration.between(x.getReadingStart().toLocalTime(), x.getReadingEnd().toLocalTime())))).build()).collect(Collectors.toList());
    }
}
