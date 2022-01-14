package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.Writing;

import java.util.List;

public interface WritingRepository  extends JpaRepository<Writing, Long> {
    List<Writing> findWritingsByParentId(Long id);
    Writing findWritingById(Long id);
}
