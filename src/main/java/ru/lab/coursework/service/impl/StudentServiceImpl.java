package ru.lab.coursework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lab.coursework.component.DiaryGenerator;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.model.*;
import ru.lab.coursework.repository.*;
import ru.lab.coursework.service.StudentService;

import java.io.File;
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
public class StudentServiceImpl implements StudentService {

    private final ReadingSessionRepository readingSessionRepository;
    private final DiaryRepository diaryRepository;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ReadingTaskRepository readingTaskRepository;
    private final DiaryGenerator diaryGenerator;
    private final DiaryFileRepository diaryFileRepository;
    private final ReportDiaryRepository reportDiaryRepository;

    @Override
    public void saveSession(ReadingSessionSaveRequestDTO readingSessionSaveRequestDTO) {
        ReadingSession readingSession = new ReadingSession();
        readingSession.setStudent(userRepository.findUserById(readingSessionSaveRequestDTO.getStudentId()));
        readingSession.setReadingTask(readingTaskRepository.findReadingTaskById(readingSessionSaveRequestDTO.getReadingTaskId()));
        readingSession.setDate(readingSessionSaveRequestDTO.getDate());
        readingSession.setReadingStart(readingSessionSaveRequestDTO.getReadingStart());
        readingSession.setReadingEnd(readingSessionSaveRequestDTO.getReadingEnd());
        readingSessionRepository.save(readingSession);
    }

    @Override
    public void deleteSession(IdRequestDTO idRequestDTO) {
        readingSessionRepository.deleteById(idRequestDTO.getId());
    }

    @Override
    public void saveReport(ReportSaveRequestDTO reportSaveRequestDTO) {
        Report report = reportRepository.findReportByReadingTaskId(reportSaveRequestDTO.getReadingTaskId());
        if (report == null) {
            report = new Report();
            report.setStudent(readingTaskRepository.findReadingTaskById(reportSaveRequestDTO.getReadingTaskId()).getStudent());
            report.setReadingTask(readingTaskRepository.findReadingTaskById(reportSaveRequestDTO.getReadingTaskId()));
            report.setCreationDate(new Timestamp(System.currentTimeMillis()));
        }
        report.setCharacters(reportSaveRequestDTO.getCharacters());
        report.setPlot(reportSaveRequestDTO.getPlot());
        report.setReview(reportSaveRequestDTO.getReview());
        report.setEditDate(new Timestamp(System.currentTimeMillis()));
        reportRepository.save(report);
    }
}
