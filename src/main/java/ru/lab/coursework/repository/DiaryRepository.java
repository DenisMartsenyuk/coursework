package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
