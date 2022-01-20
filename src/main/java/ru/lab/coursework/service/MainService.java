package ru.lab.coursework.service;

import com.itextpdf.text.DocumentException;
import ru.lab.coursework.dto.*;

import java.io.IOException;
import java.util.List;

public interface MainService {
    String getDiary(IdRequestDTO idRequestDTO);
    List<DiaryResponseDto> getDiaries(IdRequestDTO idRequestDTO);
    ReportResponseDTO getReport(IdRequestDTO idRequestDTO);
    List<ReadingTaskResponseDTO> getTasks(IdRequestDTO idRequestDTO, Boolean completed);
    ReadingTaskDetailsResponseDTO getReadingTaskDetails(IdRequestDTO idRequestDTO);
    ReadingTaskSessionsResponseDTO getReadingTaskSessions(IdRequestDTO idRequestDTO);
    List<ReadingSessionResponseDTO> getReadingSessions(IdRequestDTO idRequestDTO);
    AuthorResponseDTO getAuthor(IdRequestDTO idRequestDTO);
    WritingResponseDTO getWriting(IdRequestDTO idRequestDTO);
    String generateDiary(DiaryGenerateRequestDTO diaryGenerateRequestDTO) throws IOException, DocumentException;
    void deleteDiary(IdRequestDTO idRequestDTO);
}
