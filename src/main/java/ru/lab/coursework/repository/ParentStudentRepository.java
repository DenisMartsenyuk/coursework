package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.ParentStudent;

import java.util.List;

public interface ParentStudentRepository extends JpaRepository<ParentStudent, Long> {
    List<ParentStudent> findParentStudentsByParentId(Long parentId);
    void deleteParentStudentByParentIdAndStudentId(Long parentId, Long studentId);
}
