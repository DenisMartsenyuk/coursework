package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.ReadingTask;

import java.util.List;

public interface ReadingTaskRepository extends JpaRepository<ReadingTask, Long> {
    ReadingTask findReadingTaskById(Long id);
    List<ReadingTask> findReadingTasksByStudentIdAndCompleted(Long id, Boolean completed);
}
