package com.example.plates.validators;

import com.example.plates.domain.CVR;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CVRValidator implements Validator {
    @Override
    public boolean supports(Class<?> type) {

        return type.equals(CVR.class);
    }

    @Override
    public  void validate(Object o, Errors errors) {
        CVR item = (CVR) o;
        ValidationUtils.rejectIfEmpty(errors, "username", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "address", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "natId", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "vehicleType", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "numPlate", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "engineNo", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "chassisNo", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "exemptionStatus", "item.required");
        ValidationUtils.rejectIfEmpty(errors, "amtToCharge", "item.required");
    }
}
