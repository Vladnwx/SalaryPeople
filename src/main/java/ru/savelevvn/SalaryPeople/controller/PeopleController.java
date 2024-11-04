package ru.savelevvn.SalaryPeople.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.savelevvn.SalaryPeople.HTMLParser.HTMLParser;
import ru.savelevvn.SalaryPeople.entity.People;
import ru.savelevvn.SalaryPeople.entity.Position;
import ru.savelevvn.SalaryPeople.repository.PeopleRepository;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Slf4j
@Controller
public class PeopleController {
    @Autowired
    private PeopleRepository peopleRepository;

    @GetMapping("/list")
    public ModelAndView getAllPeoples() {
        log.info("Slf4j: " + this.getClass().getName() + " Method " + Thread.currentThread().getStackTrace()[1].getMethodName() + " executed");
        log.info("/list -> connection");
        ModelAndView modelAndView = new ModelAndView("list-peoples");
        modelAndView.addObject ("peoples", peopleRepository.findAll());
        return modelAndView;
    }
    @GetMapping("/addPeopleForm")
    public ModelAndView addPeopleForm(){
        ModelAndView modelAndView = new ModelAndView("add-people-form");
        People people = new People();
        modelAndView.addObject("people", people);
        List<Position> positions = Arrays.stream(Position.values()).toList();
        modelAndView.addObject("positions", positions);
        return modelAndView;
    }
    @PostMapping("/savePeople")
    public String savePeople(@ModelAttribute People people){
        peopleRepository.save(people);
        return "redirect:/list";
    }
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long peopleId) {
        ModelAndView modelAndView = new ModelAndView("add-people-form");
        Optional<People> optionalPeople = peopleRepository.findById(peopleId);
        People people = new People();
        if(optionalPeople.isPresent()){
            people = optionalPeople.get();
        }
        modelAndView.addObject("people", people);
        List<Position> positions = Arrays.stream(Position.values()).toList();
        modelAndView.addObject("positions", positions);
        return modelAndView;
    }
    @GetMapping("/deletePeople")
    public String deletePeople(@RequestParam Long peopleId){
        peopleRepository.deleteById(peopleId);
        return "redirect:/list";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(){
        peopleRepository.deleteAll();
        return "redirect:/list";
    }

    @GetMapping("/addPeopleFormFile")
    public String addPeopleFormFile() throws IOException {
      //  ModelAndView modelAndView = new ModelAndView("add-people-from-file");
        List<People> peopleList = new ArrayList<>();

        HTMLParser htmlParser = new HTMLParser();
        peopleList = htmlParser.getPeopleList("test.html");

      //  People people = new People();

    for(People people : peopleList) {
        peopleRepository.save(people);
   //     modelAndView.addObject("people", people);
    }
       // return modelAndView;
        return "redirect:/list";
    }

}
