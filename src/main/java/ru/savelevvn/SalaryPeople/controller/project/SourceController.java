package ru.savelevvn.SalaryPeople.controller.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.savelevvn.SalaryPeople.entity.Project;
import ru.savelevvn.SalaryPeople.entity.Source;
import ru.savelevvn.SalaryPeople.repository.ProjectRepository;
import ru.savelevvn.SalaryPeople.repository.SourceRepository;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = "/source")
public class SourceController {
    @Autowired
    private SourceRepository sourceRepository;

    @GetMapping("/list")
    public ModelAndView getAllSources() {
        log.info("Slf4j: " + this.getClass().getName() + " Method " + Thread.currentThread().getStackTrace()[1].getMethodName() + " executed");
        ModelAndView modelAndView = new ModelAndView("./source/list-source");
        modelAndView.addObject ("sources", sourceRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/addForm")
    public ModelAndView addSourceForm(){
        ModelAndView modelAndView = new ModelAndView("./source/add-source-form");
        Source source = new Source();
        modelAndView.addObject("source", source);
        return modelAndView;
    }
    @PostMapping("/save")
    public String saveSource(@ModelAttribute Source source){
        sourceRepository.save(source);
        return "redirect:/source/list";
    }
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long sourceId) {
        ModelAndView modelAndView = new ModelAndView("./source/add-source-form");
        Optional<Source> optionalSource = sourceRepository.findById(sourceId);
        Source source = new Source();
        if(optionalSource.isPresent()){
            source = optionalSource.get();
        }
        modelAndView.addObject("source", source);
        return modelAndView;
    }
    @GetMapping("/delete")
    public String deleteProject(@RequestParam Long sourceId){
        sourceRepository.deleteById(sourceId);
        return "redirect:/source/list";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(){
        sourceRepository.deleteAll();
        return "redirect:/source/list";
    }
}
