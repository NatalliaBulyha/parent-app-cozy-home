package com.cozyhome.onlineshop.productservice.validation.impl;

import com.cozyhome.onlineshop.productservice.validation.ValidSkuCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SkuCodeConstraintValidator implements ConstraintValidator<ValidSkuCode, String> {

    @Override
    public void initialize(ValidSkuCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String skuCode, ConstraintValidatorContext constraintValidatorContext) {
        return skuCode != null
            && skuCode.matches("^\\d{6}$");
    }
}
