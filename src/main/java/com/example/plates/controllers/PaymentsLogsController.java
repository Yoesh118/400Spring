package com.example.plates.controllers;

import com.example.plates.repositories.InsuranceRepository;
import com.example.plates.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PaymentsLogsController {

    @Autowired
    PaymentsRepository paymentsRepository;

    @RequestMapping(value = "/pLogs", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showForm(Model model) {
        model.addAttribute("payments", paymentsRepository.findAll());
        return new ModelAndView("paymentsLogs");
    }


}
