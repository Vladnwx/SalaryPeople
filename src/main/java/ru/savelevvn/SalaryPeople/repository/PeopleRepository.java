package ru.savelevvn.SalaryPeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.savelevvn.SalaryPeople.entity.People;


public interface PeopleRepository extends JpaRepository<People, Long> {
}