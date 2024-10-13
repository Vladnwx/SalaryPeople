package ru.savelevvn.SalaryPeople.dao;

import org.springframework.stereotype.Repository;
import ru.savelevvn.SalaryPeople.entity.People;

import java.util.List;

@Repository
public interface PeopleDAO {

    List<People> getAllPeople();

    People savePeople(People people);

    People getPeople (int id);
    void deletePeople(int id);

}
