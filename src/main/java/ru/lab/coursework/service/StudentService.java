package ru.lab.coursework.service;

import ru.lab.coursework.dto.DiaryGenerateRequestDTO;
import ru.lab.coursework.dto.IdRequestDTO;
import ru.lab.coursework.dto.ReadingSessionSaveRequestDTO;
import ru.lab.coursework.dto.ReportSaveRequestDTO;

public interface StudentService {
    void saveSession(ReadingSessionSaveRequestDTO readingSessionSaveRequestDTO);
    void deleteSession(IdRequestDTO idRequestDTO);
    void saveReport(ReportSaveRequestDTO reportSaveRequestDTO);
    void generateDiary(DiaryGenerateRequestDTO diaryGenerateRequestDTO);
    void deleteDiary(IdRequestDTO idRequestDTO);
}
