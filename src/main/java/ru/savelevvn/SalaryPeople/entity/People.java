package ru.savelevvn.SalaryPeople.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "peoples")
//Это сущность человека
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    //Проект
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "people_project",
            joinColumns = { @JoinColumn(name = "people_id", referencedColumnName =  "id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName =  "id")}
    )
    private Collection<Project> projects;

    //Участок
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "people_source",
            joinColumns = { @JoinColumn(name = "people_id", referencedColumnName =  "id")},
            inverseJoinColumns = {@JoinColumn(name = "source_id", referencedColumnName =  "id")}
    )
    private Collection<Source> sources;

    @Column(name = "age")
    private Integer age;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "position")
    private Position position = Position.NOT_INITIALIZED;

    @OneToOne(mappedBy = "people")
    private User user;


    public People(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = Position.NOT_INITIALIZED;
    }
}
