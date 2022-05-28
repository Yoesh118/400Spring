package com.example.plates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SiteController {

    @Autowired


    @RequestMapping(value = "/site", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("site");
        return modelAndView;
    }

}
