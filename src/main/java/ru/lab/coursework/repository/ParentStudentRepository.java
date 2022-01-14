package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.ParentStudent;

public interface ParentStudentRepository extends JpaRepository<ParentStudent, Long> {
}
