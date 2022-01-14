package ru.lab.coursework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rd_role_permission")
@Data
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
}
