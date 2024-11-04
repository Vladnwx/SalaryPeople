package ru.savelevvn.SalaryPeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.savelevvn.SalaryPeople.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
