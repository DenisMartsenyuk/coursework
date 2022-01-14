package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.ReadingSession;

public interface ReadingSessionRepository extends JpaRepository<ReadingSession, Long> {
}
