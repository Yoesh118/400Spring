package com.example.plates.validators;

import com.example.plates.domain.Payments;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PaymentsValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(Payments.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Payments item = (Payments) o;
        ValidationUtils.rejectIfEmpty(errors, "plateNo", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "method", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "amnt", "item.required");
    }
}
