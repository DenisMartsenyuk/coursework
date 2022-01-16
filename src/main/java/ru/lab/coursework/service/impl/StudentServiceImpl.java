package ru.lab.coursework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lab.coursework.dto.IdRequestDTO;
import ru.lab.coursework.dto.ReadingSessionSaveRequestDTO;
import ru.lab.coursework.dto.ReportSaveRequestDTO;
import ru.lab.coursework.model.ReadingSession;
import ru.lab.coursework.model.Report;
import ru.lab.coursework.repository.*;
import ru.lab.coursework.service.StudentService;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final ReadingSessionRepository readingSessionRepository;
    private final DiaryRepository diaryRepository;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ReadingTaskRepository readingTaskRepository;

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

    @Override
    public void deleteDiary(IdRequestDTO idRequestDTO) {
        diaryRepository.deleteById(idRequestDTO.getId());
    }
}
