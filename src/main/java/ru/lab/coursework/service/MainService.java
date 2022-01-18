package ru.lab.coursework.service;

import ru.lab.coursework.dto.*;

import java.util.List;

public interface MainService {
    List<DiaryResponseDto> getDiaries(IdRequestDTO idRequestDTO);
    ReportResponseDTO getReport(IdRequestDTO idRequestDTO);
    List<ReadingTaskResponseDTO> getTasks(IdRequestDTO idRequestDTO, Boolean completed);
    ReadingTaskDetailsResponseDTO getReadingTaskDetails(IdRequestDTO idRequestDTO);
    ReadingTaskSessionsResponseDTO getReadingTaskSessions(IdRequestDTO idRequestDTO);
    List<ReadingSessionResponseDTO> getReadingSessions(IdRequestDTO idRequestDTO);
    AuthorResponseDTO getAuthor(IdRequestDTO idRequestDTO);
    WritingResponseDTO getWriting(IdRequestDTO idRequestDTO);
    String generateDiary(DiaryGenerateRequestDTO diaryGenerateRequestDTO);
    void deleteDiary(IdRequestDTO idRequestDTO);
}
