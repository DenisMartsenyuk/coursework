package ru.lab.coursework.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Table(name = "rd_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "middle_name")
    private String middle_name;

    @Column(name = "email")
    private String email;
}
