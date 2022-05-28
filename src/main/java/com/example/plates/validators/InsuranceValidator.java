package com.example.plates.validators;

import com.example.plates.domain.Insurance;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class InsuranceValidator implements Validator {
    @Override
    public boolean supports(Class<?> type) {
        return type.equals(Insurance.class);
    }

    @Override
    public  void validate(Object o, Errors errors) {
        Insurance item = (Insurance) o;
        ValidationUtils.rejectIfEmpty(errors, "name", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "plateNo", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "insuranceCo", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "expiryDate", "item.required");
        }
}
