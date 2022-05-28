package com.example.plates.controllers;

import com.example.plates.domain.CVR;
import com.example.plates.repositories.CVRRepository;
import com.example.plates.validators.CVRValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CVRController {

    @Autowired
    CVRRepository cvrRepository;

    @Resource
    CVRValidator cvrValidator;
    Logger logger = LoggerFactory.getLogger(CVRController.class);

    
    @RequestMapping(value = "/cvr", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showForm(ModelMap model) {
        model.addAttribute("cvr", new CVR());
        ModelAndView modelAndView = new ModelAndView("cvr");
        return modelAndView;
    }

    @PostMapping(value = "/save")
    public ModelAndView submit(@Validated @ModelAttribute CVR cvr, BindingResult result, ModelMap model) {
        cvrValidator.validate(cvr,result);
        CVR s = new CVR();
        s.setAddress(cvr.getAddress());
        s.setExemptionStatus(cvr.getExemptionStatus());
        s.setChassisNo(cvr.getChassisNo());
        s.setAmtToCharge(cvr.getAmtToCharge());
        s.setEngineNo(cvr.getEngineNo());
        s.setNatId(cvr.getNatId());
        s.setEngineNo(cvr.getEngineNo());
        s.setUsername(cvr.getUsername());
        s.setNumPlate(cvr.getNumPlate());
        s.setVehicleType(cvr.getVehicleType());

        List<CVR> p = cvrRepository.findPaymentsByNumPlate(cvr.getNumPlate());
        int length=p.size();
        if (length!=0){
            model.addAttribute("cvr", new CVR());
            return new ModelAndView("cvr2");
        }

        model.addAttribute("cvr", cvr );
        cvrRepository.save(s);
        model.addAttribute("cvr", cvrRepository.findAll());
        return new ModelAndView("cvrLogs");
    }

    @Transactional
    @GetMapping(value = "/deleteC/{id}")
    public ModelAndView deleteRecord(@PathVariable Long id, ModelMap model) {
        cvrRepository.deleteC(id);
        model.addAttribute("cvr", cvrRepository.findAll());
        return new ModelAndView("cvrLogs");
    }

    @Transactional
    @GetMapping(value = "/updateC/{id}")
    public ModelAndView updateRecord(@PathVariable Long id, ModelMap model) {
        cvrRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("cvr");
        return modelAndView;
    }

    }

