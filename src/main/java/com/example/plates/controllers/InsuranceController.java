package com.example.plates.controllers;

import com.example.plates.domain.CVR;
import com.example.plates.domain.Insurance;
import com.example.plates.repositories.InsuranceRepository;
import com.example.plates.validators.AppMessage;
import com.example.plates.validators.InsuranceValidator;
import com.example.plates.validators.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
public class InsuranceController {

    @Autowired
    InsuranceRepository insuranceRepository;

    @Resource
    InsuranceValidator insuranceValidator;


    @RequestMapping(value = "/insurance", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showForm(ModelMap model) {
        model.addAttribute("insurance", new Insurance());
        ModelAndView modelAndView = new ModelAndView("insurance");
        return modelAndView;
    }


    @PostMapping(value = "/saveI")
    public ModelAndView submit(@Validated @ModelAttribute Insurance insurance, BindingResult result, ModelMap model) {
        insuranceValidator.validate(insurance,result);
        Insurance s = new Insurance();
        model.addAttribute("insurance", insurance );
        s.setInsuranceCo(insurance.getInsuranceCo());
        s.setExpiryDate(insurance.getExpiryDate());
        s.setName(insurance.getName());
        s.setPlateNo(insurance.getPlateNo());
        insuranceRepository.save(s);
        model.addAttribute("insurance", insuranceRepository.findAll());
        return new ModelAndView("insuranceLogs");
    }

    @Transactional
    @GetMapping(value = "/deleteI/{id}")
    public ModelAndView deleteRecord(@PathVariable Long id, ModelMap model) {
        insuranceRepository.deleteI(id);
        model.addAttribute("insurance", insuranceRepository.findAll());
        return new ModelAndView("insuranceLogs");
    }


}
