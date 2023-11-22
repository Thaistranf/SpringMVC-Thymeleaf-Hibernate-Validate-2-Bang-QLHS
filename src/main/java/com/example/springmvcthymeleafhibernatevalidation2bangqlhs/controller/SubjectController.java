package com.example.springmvcthymeleafhibernatevalidation2bangqlhs.controller;

import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.model.Subject;
import com.example.springmvcthymeleafhibernatevalidation2bangqlhs.service.subject.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    @GetMapping
    public ModelAndView showSubjectList(){
        ModelAndView modelAndView = new ModelAndView("/subject/list");
        modelAndView.addObject("listSubject", subjectService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateSubject(){
        ModelAndView modelAndView = new ModelAndView("/subject/create");
        modelAndView.addObject("createSubject", new Subject());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(Subject subject){
        subjectService.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable Long id){
        Subject subject = subjectService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("/subject/update");
        modelAndView.addObject("subjectUp", subject);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, Subject subject){
        subjectService.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        subjectService.remove(id);
        return "redirect:/subjects";
    }
}
