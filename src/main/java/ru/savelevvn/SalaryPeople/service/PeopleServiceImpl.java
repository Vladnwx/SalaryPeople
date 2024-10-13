package ru.savelevvn.SalaryPeople.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import ru.savelevvn.SalaryPeople.dao.PeopleDAO;
import ru.savelevvn.SalaryPeople.entity.People;

import java.util.List;

public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleDAO peopleDAO;

    @Override
    @Transactional
    public List<People> getAllPeople() {
        return peopleDAO.getAllPeople();
    }

    @Override
    @Transactional
    public People savePeople(People people) {

        return peopleDAO.savePeople(people);
    }

    @Override
    @Transactional
    public People getPeople(int id) {

        return peopleDAO.getPeople(id);
    }

    @Override
    @Transactional
    public void deletePeople(int id) {
        peopleDAO.deletePeople(id);
    }
}
