package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.Author;

import java.util.List;

public interface AuthorRepository  extends JpaRepository<Author, Long> {
    List<Author> findAuthorByParentId(Long id);
}
