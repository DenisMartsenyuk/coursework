package ru.lab.coursework.service;

import ru.lab.coursework.dto.*;

import java.util.List;

public interface MainService {
    List<DiaryResponseDto> getDiaries(IdRequestDTO idRequestDTO);
    ReportResponseDTO getReport(IdRequestDTO idRequestDTO);
    List<ReadingTaskResponseDTO> getTasks(IdRequestDTO idRequestDTO, Boolean completed);
    List<ReadingSessionResponseDTO> getReadingSessions(IdRequestDTO idRequestDTO);
    ReadingTaskDetailsResponseDTO getReadingTaskDetails(IdRequestDTO idRequestDTO);
}
