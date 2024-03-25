package com.idatt2105.backend.util;

import java.util.Collection;
import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoNullElementsValidator implements ConstraintValidator<NoNullElements, Collection<?>> {
  @Override
  public boolean isValid(Collection<?> collection, ConstraintValidatorContext context) {
    if (collection == null) return false;
    return collection.stream().noneMatch(Objects::isNull);
  }
}
