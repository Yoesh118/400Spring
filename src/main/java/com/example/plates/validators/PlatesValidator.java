package com.example.plates.validators;

import com.example.plates.domain.Plates;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PlatesValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(Plates.class);
    }

    @Override
    public  void validate(Object o, Errors errors) {
        Plates item = (Plates) o;
      //  ValidationUtils.rejectIfEmpty(errors, "content", "item.required");
    //    ValidationUtils.rejectIfEmpty(errors, "numPlate", "item.required");
        }
}
