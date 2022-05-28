package com.example.plates.controllers;

import com.example.plates.domain.Plates;
import com.example.plates.repositories.PlatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LogsController {

    @Autowired
    PlatesRepository platesRepository;

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showForm(Model model) {
        model.addAttribute("plates", platesRepository.findAll());
        return new ModelAndView("logs");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView update(ModelMap model) {
        model.addAttribute("plates", new Plates());
        ModelAndView modelAndView = new ModelAndView("updatePlates");
        return modelAndView;

    }

}
