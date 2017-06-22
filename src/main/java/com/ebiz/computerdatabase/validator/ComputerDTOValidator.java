package com.ebiz.computerdatabase.validator;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.exception.BadRequestException;
import com.ebiz.computerdatabase.mapper.DataMapper;
import org.springframework.validation.ValidationUtils;

import java.time.LocalDate;

/**
 * Created by ebiz on 22/06/17.
 */
public class ComputerDTOValidator {

    public static void validArgument(ComputerDTO computerDTO){

        LocalDate introducedLocalDate = null;
        if(computerDTO.getIntroduced()!=null) {
            introducedLocalDate = DataMapper.convertStringToDate(computerDTO.getIntroduced());
            if(introducedLocalDate == null){
                throw new BadRequestException("introduced is Required or not to format yyyy-MM-dd");
            }
        }
        LocalDate discontinuedLocalDate=null;
        if(computerDTO.getDiscontinued()!=null){
           discontinuedLocalDate = DataMapper.convertStringToDate(computerDTO.getDiscontinued());
            if(discontinuedLocalDate == null){
                throw new BadRequestException("discontinued is Required or not to format yyyy-MM-dd");
            }
        }

        if(computerDTO.getName()==null){
            throw new BadRequestException("Name is required");
        }
        if(introducedLocalDate!=null && discontinuedLocalDate!=null && discontinuedLocalDate.isBefore(introducedLocalDate)){

            throw new BadRequestException("introduced can't be after discontinued");
        }
        if(computerDTO.getCompanyId() <= 0){
            throw new BadRequestException("companyId is Required");
        }
    }
}
