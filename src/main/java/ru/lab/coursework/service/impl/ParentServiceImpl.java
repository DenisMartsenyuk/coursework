package ru.lab.coursework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lab.coursework.dto.*;
import ru.lab.coursework.model.*;
import ru.lab.coursework.repository.*;
import ru.lab.coursework.service.ParentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentStudentRepository parentStudentRepository;
    private final AuthorRepository authorRepository;
    private final WritingRepository writingRepository;
    private final UserRepository userRepository;
    private final ReadingTaskRepository readingTaskRepository;

    @Override
    public ChildResponseDTO getChild(IdRequestDTO idRequestDTO) {
        User user = userRepository.findUserById(idRequestDTO.getId());
        return ChildResponseDTO.builder().id(user.getId()).name(user.getName()).surname(user.getSurname()).middleName(user.getMiddleName()).build();
    }

    @Override
    public List<ChildResponseDTO> getChildren(IdRequestDTO idRequestDTO) {
        List<ParentStudent> parentStudents = parentStudentRepository.findParentStudentsByParentId(idRequestDTO.getId());
        return parentStudents.stream().map(x -> ChildResponseDTO.builder().id(x.getStudent().getId()).name(x.getStudent().getName()).surname(x.getStudent().getSurname()).middleName(x.getStudent().getMiddleName()).build()).collect(Collectors.toList());
    }

    @Override
    public List<AuthorResponseDTO> getAuthors(IdRequestDTO idRequestDTO) {
        List<Author> authors = authorRepository.findAuthorsByParentId(idRequestDTO.getId());
        return authors.stream().map(x -> AuthorResponseDTO.builder().id(x.getId()).name(x.getName()).surname(x.getSurname()).middleName(x.getMiddleName()).build()).collect(Collectors.toList());
    }

    @Override
    public List<WritingResponseDTO> getWritings(IdRequestDTO idRequestDTO) {
        List<Writing> writings = writingRepository.findWritingsByParentId(idRequestDTO.getId());
        return writings.stream().map(x -> WritingResponseDTO.builder().id(x.getId()).name(x.getName()).author(AuthorResponseDTO.builder().id(x.getAuthor().getId()).name(x.getAuthor().getName()).surname(x.getAuthor().getSurname()).middleName(x.getAuthor().getMiddleName()).build()).build()).collect(Collectors.toList());
    }

    @Override
    public void saveAuthor(AuthorSaveRequestDTO authorSaveRequestDTO) {
        Author author = new Author();
        author.setId(authorSaveRequestDTO.getId());
        author.setName(authorSaveRequestDTO.getName());
        author.setSurname(authorSaveRequestDTO.getSurname());
        author.setMiddleName(authorSaveRequestDTO.getMiddleName());
        author.setParent(userRepository.findUserById(authorSaveRequestDTO.getParentId()));
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(IdRequestDTO idRequestDTO) {
        authorRepository.deleteById(idRequestDTO.getId());
    }

    @Override
    public void saveWriting(WritingSaveRequestDTO writingSaveRequestDTO) {
        Writing writing = new Writing();
        writing.setId(writingSaveRequestDTO.getId());
        writing.setParent(userRepository.findUserById(writingSaveRequestDTO.getParentId()));
        writing.setAuthor(authorRepository.findAuthorById(writingSaveRequestDTO.getAuthorId()));
        writing.setName(writingSaveRequestDTO.getName());
        writingRepository.save(writing);
    }

    @Override
    public void deleteWriting(IdRequestDTO idRequestDTO) {
        writingRepository.deleteById(idRequestDTO.getId());
    }

    @Override
    public void saveTask(ReadingTaskSaveRequestDTO readingTaskSaveRequestDTO) {
        ReadingTask readingTask = new ReadingTask();
        readingTask.setId(readingTaskSaveRequestDTO.getId());
        readingTask.setParent(userRepository.findUserById(readingTaskSaveRequestDTO.getParentId()));
        readingTask.setStudent(userRepository.findUserById(readingTaskSaveRequestDTO.getStudentId()));
        readingTask.setWriting(writingRepository.findWritingById(readingTaskSaveRequestDTO.getWritingId()));
        readingTask.setDeadline(readingTaskSaveRequestDTO.getDeadline());
        readingTask.setCompleted(readingTaskSaveRequestDTO.getCompleted());
        readingTaskRepository.save(readingTask);
    }

    @Override
    public void deleteTask(IdRequestDTO idRequestDTO) {
        readingTaskRepository.deleteById(idRequestDTO.getId());
    }
}
