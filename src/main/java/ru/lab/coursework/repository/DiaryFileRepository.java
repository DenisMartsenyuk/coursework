package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.DiaryFile;

public interface DiaryFileRepository extends JpaRepository<DiaryFile, Long> {
}
