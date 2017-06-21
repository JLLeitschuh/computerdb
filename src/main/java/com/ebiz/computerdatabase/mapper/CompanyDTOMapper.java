package com.ebiz.computerdatabase.mapper;

import com.ebiz.computerdatabase.dto.CompanyDTO;
import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.entities.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ebiz on 21/06/17.
 */
public class CompanyDTOMapper {

    public static CompanyDTO companyToDto(Company company){

        return new CompanyDTO(company.getId(),company.getName());

    }

    public static List<CompanyDTO> createCompanyDtoList(List<Company> companies){

        List<CompanyDTO> companyDTOS = new ArrayList<>();
        for(Company company: companies){

            companyDTOS.add(companyToDto(company));
        }
        return companyDTOS;
    }
}
