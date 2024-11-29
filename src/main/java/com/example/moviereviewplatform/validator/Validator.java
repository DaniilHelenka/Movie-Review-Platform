package com.example.moviereviewplatform.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
