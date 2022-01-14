package ru.lab.coursework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rd_permission")
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
