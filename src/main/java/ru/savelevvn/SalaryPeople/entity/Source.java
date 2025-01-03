package ru.savelevvn.SalaryPeople.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "source")
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

    @ManyToMany(mappedBy = "sources", fetch = FetchType.EAGER)
    private Collection<People> peoples;

}
