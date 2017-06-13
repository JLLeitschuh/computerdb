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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "introduced", "introduced.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "discontinued", "discontinued.required");
        if(computerDTO.getName() == null ){
            errors.rejectValue("name", "", new Object[]{"'computerDTO.getName()'"}, "name can't be null");
        }
        LocalDate introducedLocalDate = DataMapper.convertStringToDate(computerDTO.getIntroduced());
        LocalDate discontinuedLocalDate = DataMapper.convertStringToDate(computerDTO.getDiscontinued());
        if(introducedLocalDate==null){
            errors.rejectValue("introduced", "", new Object[]{"'introducedLocalDate'"}, "introduced can't be null");
        }
        if(discontinuedLocalDate==null){
            errors.rejectValue("discontinued", "", new Object[]{"'discontinuedLocalDate'"}, "discontinued can't be null");
        }
        if(discontinuedLocalDate != null && discontinuedLocalDate.isBefore(introducedLocalDate)){
            errors.rejectValue("discontinued", "", new Object[]{"'introducedLocalDate,discontinuedLocalDate'"}, "discontinued can't be before introduced");
        }
    }
}
