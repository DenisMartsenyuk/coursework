package ru.lab.coursework.service;

import ru.lab.coursework.dto.IdRequestDTO;
import ru.lab.coursework.dto.ReadingSessionResponseDTO;
import ru.lab.coursework.dto.ReadingTaskResponseDTO;
import ru.lab.coursework.dto.ReportResponseDTO;

import java.util.List;

public interface MainService {
    ReportResponseDTO getReport(IdRequestDTO idRequestDTO);
    List<ReadingTaskResponseDTO> getTasks(IdRequestDTO idRequestDTO, Boolean completed);
    List<ReadingSessionResponseDTO> getStatistic(IdRequestDTO idRequestDTO);
}
