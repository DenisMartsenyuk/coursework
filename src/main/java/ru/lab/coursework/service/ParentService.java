package ru.lab.coursework.service;

import ru.lab.coursework.dto.*;

import java.util.List;

public interface ParentService {
    ChildResponseDTO getChild(IdRequestDTO idRequestDTO);
    List<ChildResponseDTO> getChildren(IdRequestDTO idRequestDTO);
    List<AuthorResponseDTO> getAuthors(IdRequestDTO idRequestDTO);
    List<WritingResponseDTO> getWritings(IdRequestDTO idRequestDTO);
    void saveAuthor(AuthorSaveRequestDTO authorSaveRequestDTO);
    void deleteAuthor(IdRequestDTO idRequestDTO);
    void saveWriting(WritingSaveRequestDTO writingSaveRequestDTO);
    void deleteWriting(IdRequestDTO idRequestDTO);
    void saveTask(ReadingTaskSaveRequestDTO readingTaskSaveRequestDTO);
    void deleteTask(IdRequestDTO idRequestDTO);
}
