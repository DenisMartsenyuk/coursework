package ru.lab.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.coursework.model.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
}
