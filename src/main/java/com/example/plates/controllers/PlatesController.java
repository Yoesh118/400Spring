package com.example.plates.controllers;

import com.example.plates.domain.CVR;
import com.example.plates.domain.Insurance;
import com.example.plates.domain.Payments;
import com.example.plates.domain.Plates;
import com.example.plates.repositories.PaymentsRepository;
import com.example.plates.repositories.PlatesRepository;
import com.example.plates.validators.AppMessage;
import com.example.plates.validators.MessageType;
import com.example.plates.validators.PlatesValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PlatesController {

    CVR cv;
    Payments pay;
    Insurance i;
    Logger logger = LoggerFactory.getLogger(PlatesController.class);
    long millis = System.currentTimeMillis();
    java.sql.Date date = new java.sql.Date(millis);


    @Autowired
    PlatesRepository platesRepository;

    @Autowired
    PaymentsRepository paymentsRepository;

    @Resource
    PlatesValidator platesValidator;

    @PostMapping
    ModelAndView uploadImage(@RequestParam("image") MultipartFile multipartImage, @ModelAttribute Plates plates, ModelMap model, BindingResult result) throws Exception {
        platesValidator.validate(plates, result);
        Plates dbImage = new Plates();
        dbImage.setContent(multipartImage.getBytes());
        dbImage.setContent2(multipartImage.getBytes());
        dbImage.setPassed("False");

        Long plate_id = platesRepository.save(dbImage).getId();
        String url = String.format("http://localhost:8000/core/api/v1/process/"
                + plate_id + "/");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        List<String> testPlate = platesRepository.findByPId(plate_id);
        logger.info(String.valueOf(testPlate));
        logger.info("=====>>");
        for (String p : testPlate) {
            logger.info(p);
        }
        List<Payments> pa = platesRepository.findPaymentsByPlateNo(testPlate.get(0));
        List<CVR> cvr = platesRepository.findPaymentsByNumPlate(testPlate.get(0));
        List<Insurance> insurance = platesRepository.findPaymentsByPlate(testPlate.get(0));
        logger.info(String.valueOf(pa));
        logger.info(String.valueOf(cvr));
        logger.info(String.valueOf(insurance));
        if (pa.isEmpty() || cvr.isEmpty() || insurance.isEmpty()) {
            ModelAndView modelAndView = new ModelAndView("site");
            logger.info("carnoound");

            return modelAndView;
        } else {
            pay = pa.get(0);
            cv = cvr.get(0);
            i = insurance.get(0);
        }


        if (pay.getPlateNo() != null) {
            if (pay.getBalance() != null && cv.getAmtToCharge() != null && pay.getBalance() >= cv.getAmtToCharge()) {
                if (i.getExpiryDate() != null && i.getExpiryDate().after(date)) {
                    pay.setBalance(pay.getBalance() - cv.getAmtToCharge());
                    paymentsRepository.save(pay);
                    dbImage.setPassed("True");
                    logger.info("open&&boom");
                } else {
                    ModelAndView modelAndView = new ModelAndView("isite");
                    logger.info("carnotinsured");
                    return modelAndView;

                }
            } else {
                ModelAndView modelAndView = new ModelAndView("psite");
                logger.info("insufficientfunds");
                return modelAndView;

            }
        } else {
            ModelAndView modelAndView = new ModelAndView("plsite");
            logger.info("platttenottffound");
            return modelAndView;

        }
        ModelAndView modelAndView = new ModelAndView("ssite");
        return modelAndView;
        //response.getBody();
    }

    @RequestMapping(value = "/plates", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showForm(ModelMap model) {
        model.addAttribute("plates", new Plates());
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }


    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    ByteArrayResource downloadImage(@PathVariable Long imageId) {
        byte[] image = platesRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return new ByteArrayResource(image);
    }

    @PostMapping(value = "/searchP")
    public ModelAndView search(@ModelAttribute Plates plates, ModelMap model) {
        model.addAttribute("plates", platesRepository.findByNumPlate(plates.getNumPlate()));
        logger.info(plates.getNumPlate());
        return new ModelAndView("logs");
    }

    @Transactional
    @GetMapping(value = "/deleteP/{id}")
    public ModelAndView deleteRecord(@PathVariable Long id, ModelMap model) {
        platesRepository.deleteP(id);
        model.addAttribute("plates", platesRepository.findAll());
        return new ModelAndView("logs");
    }


}
