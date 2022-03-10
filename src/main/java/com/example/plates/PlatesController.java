package com.example.plates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
public class PlatesController {

    @Autowired
    PlatesRepository platesRepository;

    @PostMapping
    String uploadImage(@RequestParam("image") MultipartFile multipartImage) throws Exception {
        Plates dbImage = new Plates();
        dbImage.setNumPlate(multipartImage.getName());
        dbImage.setContent(multipartImage.getBytes());
        String url = String.format("http://localhost:8000/core/api/v1/process/"
                + platesRepository.save(dbImage).getId() + "/");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
        }

    @RequestMapping(value = "/plates", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("platesForm");
        return modelAndView;
    }




    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource downloadImage(@PathVariable Long imageId) {
        byte[] image = platesRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return (Resource) new ByteArrayResource(image);
    }
}
