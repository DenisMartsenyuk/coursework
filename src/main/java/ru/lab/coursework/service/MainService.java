package ru.lab.coursework.service;

import ru.lab.coursework.dto.IdRequestDTO;
import ru.lab.coursework.dto.ReadingSessionResponseDTO;
import ru.lab.coursework.dto.ReadingTaskResponseDTO;

import java.util.List;

public interface MainService {
    List<ReadingTaskResponseDTO> getTasks(IdRequestDTO idRequestDTO, Boolean completed);
    List<ReadingSessionResponseDTO> getStatistic(IdRequestDTO idRequestDTO);
}
