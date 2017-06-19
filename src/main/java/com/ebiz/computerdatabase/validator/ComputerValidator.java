package com.ebiz.computerdatabase.validator;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.mapper.DataMapper;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

/**
 * Created by ebiz on 12/06/17.
 */
public class ComputerValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return ComputerDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ComputerDTO computerDTO = (ComputerDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");


        LocalDate introducedLocalDate = DataMapper.convertStringToDate(computerDTO.getIntroduced());
        LocalDate discontinuedLocalDate = DataMapper.convertStringToDate(computerDTO.getDiscontinued());

        if(introducedLocalDate == null){
            errors.rejectValue("introduced", "introduced.required", new Object[]{"'introducedLocalDate,discontinuedLocalDate'"}, "introduced.required");
        }
        if(introducedLocalDate!=null && discontinuedLocalDate!=null && discontinuedLocalDate.isBefore(introducedLocalDate)){
            errors.rejectValue("discontinued", "discontinued.before", new Object[]{"'introducedLocalDate,discontinuedLocalDate'"}, "discontinued.before");
        }else if(discontinuedLocalDate == null){
            errors.rejectValue("discontinued", "discontinued.required", new Object[]{"'discontinuedLocalDate'"}, "discontinued.required");
        }
    }
}
