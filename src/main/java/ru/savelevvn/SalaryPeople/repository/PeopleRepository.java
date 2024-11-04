package ru.savelevvn.SalaryPeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.savelevvn.SalaryPeople.entity.People;
import ru.savelevvn.SalaryPeople.entity.Position;
import ru.savelevvn.SalaryPeople.entity.User;


public interface PeopleRepository extends JpaRepository<People, Long> {

    People findByPosition(Position position);

}
