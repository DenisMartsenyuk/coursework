package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.Diary;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    void deleteById(Long id);
    List<Diary> findDiariesByStudentId(Long id);
}
