package com.idatt2105.backend.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

/** Annotation for validating that a collection does not contain null elements. */
@Documented
@Constraint(validatedBy = NoNullElementsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@NotNull public @interface NoNullElements {
  String message() default "The collection cannot contain null elements";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
