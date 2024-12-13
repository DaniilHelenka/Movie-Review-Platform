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


        if (Role.find(object.getRole()).isEmpty()) {
            validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }
        return validationResult;
    }


    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
