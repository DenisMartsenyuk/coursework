package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.ReadingTask;

public interface ReadingTaskRepository extends JpaRepository<ReadingTask, Long> {
}
