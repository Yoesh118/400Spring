package com.example.plates.controllers;

import com.example.plates.repositories.CVRRepository;
import com.example.plates.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class InsuranceLogsController {

    @Autowired
    InsuranceRepository insuranceRepository;

    @RequestMapping(value = "/iLogs", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showForm(Model model) {
        model.addAttribute("insurance", insuranceRepository.findAll());
        return new ModelAndView("insuranceLogs");
    }


}
