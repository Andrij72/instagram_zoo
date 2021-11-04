package com.akul.instagramzoo.validations;

import com.akul.instagramzoo.annotations.PasswordMatches;
import com.akul.instagramzoo.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
   public void initialize(PasswordMatches constraintAnnotation) {
   }

   public boolean isValid(Object obj, ConstraintValidatorContext context) {
              SignupRequest userSignupRequest = (SignupRequest) obj;
  return userSignupRequest.getPassword().equals(userSignupRequest.getConfirmPassword());
   }
}
