package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findReportByReadingTaskId(Long id);
}
