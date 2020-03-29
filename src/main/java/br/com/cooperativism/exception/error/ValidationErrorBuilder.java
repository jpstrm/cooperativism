package br.com.cooperativism.exception.error;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationErrorBuilder {

  public static ValidationError fromBindingErrors(final Errors errors) {
    final ValidationError error = new ValidationError(
        "Validation failed. " + errors.getErrorCount() + " error(s)");
    for (final ObjectError objectError : errors.getAllErrors()) {
      final String field = ((FieldError) objectError).getField();
      error.addValidationError(
          "Field '" + field + "': " + objectError.getDefaultMessage());
    }
    return error;
  }
}
