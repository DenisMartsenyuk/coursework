package ru.lab.coursework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lab.coursework.component.DiaryGenerator;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.model.Author;
import ru.lab.coursework.model.ReadingSession;
import ru.lab.coursework.model.Report;
import ru.lab.coursework.model.User;
import ru.lab.coursework.repository.*;
import ru.lab.coursework.service.StudentService;

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
    public void generateDiary(DiaryGenerateRequestDTO diaryGenerateRequestDTO) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    @Override
    public void deleteDiary(IdRequestDTO idRequestDTO) {
        diaryRepository.deleteById(idRequestDTO.getId());
    }
}
