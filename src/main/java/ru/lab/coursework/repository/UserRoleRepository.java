package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.Role;
import ru.lab.coursework.model.User;
import ru.lab.coursework.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
