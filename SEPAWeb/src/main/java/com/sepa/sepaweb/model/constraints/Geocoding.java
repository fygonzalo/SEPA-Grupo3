package com.sepa.sepaweb.model.constraints;

import com.sepa.sepaweb.model.constraints.validator.GeocodingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GeocodingValidator.class)
public @interface Geocoding {
    String message() default "Hubo un problema para localizarte, intenta de nuevo.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
