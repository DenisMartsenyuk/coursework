package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.ReadingSession;

import java.util.List;

public interface ReadingSessionRepository extends JpaRepository<ReadingSession, Long> {
    void deleteById(Long id);
    List<ReadingSession> findReadingSessionsByReadingTaskId(Long id);
}
