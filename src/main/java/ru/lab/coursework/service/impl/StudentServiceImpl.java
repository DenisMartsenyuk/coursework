package ru.lab.coursework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lab.coursework.component.DiaryGenerator;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.model.*;
import ru.lab.coursework.repository.*;
import ru.lab.coursework.service.StudentService;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final ReadingSessionRepository readingSessionRepository;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ReadingTaskRepository readingTaskRepository;
    private final ParentStudentRepository parentStudentRepository;

    @Override
    public List<ParentResponseDTO> getParents(IdRequestDTO idRequestDTO) {
        List<ParentStudent> parentStudents = parentStudentRepository.findParentStudentsByStudentId(idRequestDTO.getId());
        return parentStudents.stream().map(x -> ParentResponseDTO.builder().id(x.getParent().getId()).name(x.getParent().getName()).surname(x.getParent().getSurname()).middleName(x.getParent().getMiddleName()).build()).collect(Collectors.toList());
    }

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
