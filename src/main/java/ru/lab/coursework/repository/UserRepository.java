package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.User;

//работа с бд по юзеру
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
    User findUserById(Long id);
}
