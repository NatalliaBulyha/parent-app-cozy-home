package com.cozyhome.onlineshop.productservice.validation.impl;

import com.cozyhome.onlineshop.productservice.validation.ValidPageNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageNumberConstraintValidator implements ConstraintValidator<ValidPageNumber, Integer> {
    @Override
    public void initialize(ValidPageNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext constraintValidatorContext) {
        return page.toString().matches("[0-9]+");
    }
}
