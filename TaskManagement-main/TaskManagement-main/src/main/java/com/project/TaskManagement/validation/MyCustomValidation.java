package com.project.TaskManagement.validation;

import com.project.TaskManagement.entity.Task;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MyCustomValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }


    @Override
    public void validate(Object target, Errors errors) {
        Task task= (Task) target;

        if(!task.getTitle().matches("^.*[a-zA-Z].*$")){
            errors.rejectValue("title" , "err.title.letter");
        }
        if(!task.getTitle().matches("^[0-9A-Za-z\\s]+$")){
            errors.rejectValue("title" , "err.character");

        }

        if(!task.getTitle().matches("^[0-9A-Za-z\\s]+$")){
            errors.rejectValue("content" , "err.character");

        }

    }
}
