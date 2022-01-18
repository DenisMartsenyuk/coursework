package ru.lab.coursework.service;

import ru.lab.coursework.dto.*;

import java.util.List;

public interface StudentService {
    List<ParentResponseDTO> getParents(IdRequestDTO idRequestDTO);
    void saveSession(ReadingSessionSaveRequestDTO readingSessionSaveRequestDTO);
    void deleteSession(IdRequestDTO idRequestDTO);
    void saveReport(ReportSaveRequestDTO reportSaveRequestDTO);
}
