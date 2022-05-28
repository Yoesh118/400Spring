package com.example.plates.controllers;

import com.example.plates.domain.CVR;
import com.example.plates.domain.Payments;
import com.example.plates.repositories.PaymentsRepository;
import com.example.plates.validators.AppMessage;
import com.example.plates.validators.MessageType;
import com.example.plates.validators.PaymentsValidator;
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
public class PaymentsController {
    Logger logger = LoggerFactory.getLogger(Payments.class);
    @Autowired
    PaymentsRepository paymentsRepository;

    @Resource
    PaymentsValidator paymentsValidator;


    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showForm(ModelMap model) {
        model.addAttribute("payments", new Payments());
        ModelAndView modelAndView = new ModelAndView("payments");
        return modelAndView;
    }



    @PostMapping(value = "/saveP")
    public ModelAndView submit(@Validated @ModelAttribute Payments payments, BindingResult result, ModelMap model) {
        paymentsValidator.validate(payments,result);
        Payments s = new Payments();
        s.setAmnt(payments.getAmnt());
        s.setMethod(payments.getMethod());
        s.setPlateNo(payments.getPlateNo());

        List<CVR> c = paymentsRepository.findLimitByPlateNo(payments.getPlateNo());
       /* for(CVR p:c) {
            if (payments.getBalance() < p.getAmtToCharge() * 2) {
                model.addAttribute("payments", new Payments());
                ModelAndView modelAndView = new ModelAndView("payments2");
                return modelAndView;
            }
        }*/

        model.addAttribute("payments", payments);
        paymentsRepository.save(s);


        List<Payments> pa =paymentsRepository.findPaymentsByPlateNo(s.getPlateNo());
        logger.info(String.valueOf(pa.size()));
        if (pa.size()<=1 || pa.get(1).getBalance()==null){
            s.setBalance(payments.getAmnt());
        }
        else  {
            Payments pay=pa.get(1);
                s.setBalance(pay.getBalance() + s.getAmnt());
        }
        paymentsRepository.save(s);
        model.addAttribute("payments", paymentsRepository.findAll());
        return new ModelAndView("paymentsLogs");
    }

    @Transactional
    @GetMapping(value = "/deletePA/{id}")
    public ModelAndView deleteRecord(@PathVariable Long id, ModelMap model) {
        paymentsRepository.deletePA(id);
        model.addAttribute("payments", paymentsRepository.findAll());
        return new ModelAndView("paymentsLogs");
    }

}
