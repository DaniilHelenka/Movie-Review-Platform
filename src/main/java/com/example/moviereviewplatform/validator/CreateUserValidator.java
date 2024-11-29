package com.example.moviereviewplatform.validator;

import com.example.moviereviewplatform.dto.CreateUserDto;
import com.example.moviereviewplatform.entity.Role;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto>{
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();


        return validationResult;
    }


    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
