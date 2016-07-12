package com.sepa.sepaweb.model.constraints;

import java.lang.annotation.Documented;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EAN {

    String message() default "{com.sepa.sepaweb.model.constraints.EAN}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
