package com.cozyhome.onlineshop.productservice.validation.impl;

import com.cozyhome.onlineshop.productservice.validation.ValidId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdConstraintValidator implements ConstraintValidator<ValidId, String> {
    @Override
    public void initialize(ValidId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return id != null
            && id.matches("[A-Za-z0-9]{24}+$");
    }
}
