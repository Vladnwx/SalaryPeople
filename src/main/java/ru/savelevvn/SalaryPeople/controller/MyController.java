package ru.savelevvn.SalaryPeople.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.savelevvn.SalaryPeople.entity.People;
import ru.savelevvn.SalaryPeople.service.PeopleService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping("/People")
    public List<People> allPeople() {
        List<People> allPeople = peopleService.getAllPeople();
        return allPeople;
    }

    @GetMapping("/People/{id}")
    public People getPeople(@PathVariable("id") int id) {
        return peopleService.getPeople(id);
    }

    @PostMapping("/People")
    public People savePeople(@RequestBody People People) {
        peopleService.savePeople(People);
        return People;
    }

    @PutMapping("/People")
    public People updatePeople(@RequestBody People People) {
        peopleService.savePeople(People);
        return People;
    }

    @DeleteMapping("/People/{id}")
    public void deletePeople(@PathVariable("id") int id) {
        peopleService.deletePeople(id);
    }




}
