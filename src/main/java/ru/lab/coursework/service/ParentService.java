package ru.lab.coursework.service;

import ru.lab.coursework.dto.AuthorResponseDTO;
import ru.lab.coursework.dto.ChildResponseDTO;
import ru.lab.coursework.dto.IdRequestDTO;
import ru.lab.coursework.dto.WritingResponseDto;
import ru.lab.coursework.model.Writing;

import java.util.List;

public interface ParentService {
    List<ChildResponseDTO> getChildren(IdRequestDTO idRequestDTO);
    List<AuthorResponseDTO> getAuthors(IdRequestDTO idRequestDTO);
    List<WritingResponseDto> getWritings(IdRequestDTO idRequestDTO);
}
