package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
