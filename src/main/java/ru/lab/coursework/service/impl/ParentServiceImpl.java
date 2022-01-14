package ru.lab.coursework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lab.coursework.dto.AuthorResponseDTO;
import ru.lab.coursework.dto.ChildResponseDTO;
import ru.lab.coursework.dto.IdRequestDTO;
import ru.lab.coursework.dto.WritingResponseDto;
import ru.lab.coursework.model.Author;
import ru.lab.coursework.model.ParentStudent;
import ru.lab.coursework.model.Writing;
import ru.lab.coursework.repository.AuthorRepository;
import ru.lab.coursework.repository.ParentStudentRepository;
import ru.lab.coursework.repository.UserRepository;
import ru.lab.coursework.repository.WritingRepository;
import ru.lab.coursework.service.ParentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentStudentRepository parentStudentRepository;
    private final AuthorRepository authorRepository;
    private final WritingRepository writingRepository;

    @Override
    public List<ChildResponseDTO> getChildren(IdRequestDTO idRequestDTO) {
        List<ParentStudent> parentStudents = parentStudentRepository.findParentStudentsByParentId(idRequestDTO.getId());
        return parentStudents.stream().map(x -> ChildResponseDTO.builder().id(x.getStudent().getId()).name(x.getStudent().getName()).surname(x.getStudent().getSurname()).middleName(x.getStudent().getMiddleName()).build()).collect(Collectors.toList());
    }

    @Override
    public List<AuthorResponseDTO> getAuthors(IdRequestDTO idRequestDTO) {
        List<Author> authors = authorRepository.findAuthorByParentId(idRequestDTO.getId());
        return authors.stream().map(x -> AuthorResponseDTO.builder().id(x.getId()).name(x.getName()).surname(x.getSurname()).middleName(x.getMiddleName()).build()).collect(Collectors.toList());
    }

    @Override
    public List<WritingResponseDto> getWritings(IdRequestDTO idRequestDTO) {
        List<Writing> writings = writingRepository.findWritingByParentId(idRequestDTO.getId());
        return writings.stream().map(x -> WritingResponseDto.builder().id(x.getId()).name(x.getName()).author(AuthorResponseDTO.builder().id(x.getAuthor().getId()).name(x.getAuthor().getName()).surname(x.getAuthor().getSurname()).middleName(x.getAuthor().getMiddleName()).build()).build()).collect(Collectors.toList());
    }
}
