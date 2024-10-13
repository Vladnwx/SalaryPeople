package ru.savelevvn.SalaryPeople.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.savelevvn.SalaryPeople.entity.People;

import java.util.List;
@Slf4j
@Repository
public class PeopleDAOImpl implements PeopleDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<People> getAllPeople() {
        Query query = entityManager.createQuery("from People");
        List<People> allPeople = query.getResultList();
        log.info("Slf4j: " + this.getClass().getName() + " Method " +
                Thread.currentThread().getStackTrace()[1].getMethodName() +
                " executed" + allPeople);
        return allPeople;
    }

    @Override
    public People savePeople(People people) {
        return entityManager.merge(people);
    }

    @Override
    public People getPeople(int id) {
        return entityManager.find(People.class, id);
    }

    @Override
    public void deletePeople(int id) {
        Query query = entityManager.createQuery("delete from People " +
                " where id=:peopleId");
        query.setParameter("peopleId", id);
        query.executeUpdate();
    }

}
