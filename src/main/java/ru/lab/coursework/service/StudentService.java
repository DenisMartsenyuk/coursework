package ru.lab.coursework.service;

import ru.lab.coursework.dto.*;

public interface StudentService {
    void saveSession(ReadingSessionSaveRequestDTO readingSessionSaveRequestDTO);
    void deleteSession(IdRequestDTO idRequestDTO);
    void saveReport(ReportSaveRequestDTO reportSaveRequestDTO);
    LinkResponseDTO generateDiary(DiaryGenerateRequestDTO diaryGenerateRequestDTO);
    void deleteDiary(IdRequestDTO idRequestDTO);
}
