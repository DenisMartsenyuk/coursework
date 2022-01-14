package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
