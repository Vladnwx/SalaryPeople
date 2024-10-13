package ru.savelevvn.SalaryPeople.service;

import org.springframework.stereotype.Service;
import ru.savelevvn.SalaryPeople.entity.People;

import java.util.List;

@Service
public interface PeopleService {
    List<People> getAllPeople();

    People savePeople(People people);

    People getPeople (int id);

    void deletePeople(int id);
}
