package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.ReportDiary;

public interface ReportDiaryRepository extends JpaRepository<ReportDiary, Long> {
}
