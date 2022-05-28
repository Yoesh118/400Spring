package com.example.plates.controllers;

import com.example.plates.domain.Plates;
import com.example.plates.repositories.PlatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

public class UpdatePlatesController {

    @Autowired
    PlatesRepository platesRepository;

    @Transactional
    @GetMapping(value = "/updateX/{id}")
    public ModelAndView updateRecord(@PathVariable Long id, ModelMap model, Plates plates) {
        Plates dbImage = new Plates();
        platesRepository.updateP(dbImage.getNewPlate(), id);
        model.addAttribute("plates", platesRepository.findByNumPlate(plates.getNumPlate()));
      //  logger.info(plates.getNumPlate());
        return new ModelAndView("logs");
    }


}
