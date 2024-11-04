package ru.savelevvn.SalaryPeople.controller.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.sqlite.ProgressHandler;
import ru.savelevvn.SalaryPeople.HTMLParser.HTMLParser;
import ru.savelevvn.SalaryPeople.entity.People;
import ru.savelevvn.SalaryPeople.entity.Position;
import ru.savelevvn.SalaryPeople.entity.Project;
import ru.savelevvn.SalaryPeople.repository.PeopleRepository;
import ru.savelevvn.SalaryPeople.repository.ProjectRepository;

import javax.swing.plaf.ProgressBarUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/list")
    public ModelAndView getAllProjects() {
        log.info("Slf4j: " + this.getClass().getName() + " Method " + Thread.currentThread().getStackTrace()[1].getMethodName() + " executed");
        ModelAndView modelAndView = new ModelAndView("./project/list-project");
        modelAndView.addObject ("projects", projectRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/addForm")
    public ModelAndView addProjectForm(){
        ModelAndView modelAndView = new ModelAndView("./project/add-project-form");
        Project project = new Project();
        modelAndView.addObject("project", project);
        return modelAndView;
    }
    @PostMapping("/save")
    public String saveProject(@ModelAttribute Project project){
        projectRepository.save(project);
        return "redirect:/project/list";
    }
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long projectId) {
        ModelAndView modelAndView = new ModelAndView("./project/add-project-form");
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        Project project = new Project();
        if(optionalProject.isPresent()){
            project = optionalProject.get();
        }
        modelAndView.addObject("project", project);
        return modelAndView;
    }
    @GetMapping("/delete")
    public String deleteProject(@RequestParam Long projectId){
        projectRepository.deleteById(projectId);
        return "redirect:/project/list";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(){
        projectRepository.deleteAll();
        return "redirect:/project/list";
    }
}
