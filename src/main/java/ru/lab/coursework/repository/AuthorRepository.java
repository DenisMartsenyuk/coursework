package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.Author;

public interface AuthorRepository  extends JpaRepository<Author, Long> {
}
