package ru.lab.coursework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rd_writing")
@Data
public class Writing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "name")
    private String name;
}
