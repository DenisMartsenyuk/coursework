package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.Writing;

public interface WritingRepository  extends JpaRepository<Writing, Long> {
}
