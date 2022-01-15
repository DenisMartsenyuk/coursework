package ru.lab.coursework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lab.coursework.dto.IdRequestDTO;
import ru.lab.coursework.dto.ReadingSessionSaveRequestDTO;
import ru.lab.coursework.dto.ReportSaveRequestDTO;
import ru.lab.coursework.model.ReadingSession;
import ru.lab.coursework.repository.*;
import ru.lab.coursework.service.StudentService;

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
//        Report report = reportRepository;
        //todo что-то сделать туть
    }

    @Override
    public void deleteDiary(IdRequestDTO idRequestDTO) {
        diaryRepository.deleteById(idRequestDTO.getId());
    }
}
