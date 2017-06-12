package com.ebiz.computerdatabase.mapper;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.model.CompanyEntity;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ebiz on 12/06/17.
 */
public class RequestMapper {

    public static ComputerDTO requestToDTO(HttpServletRequest request){
        String computerId = request.getParameter("id");
        String computerName = request.getParameter("name");
        String introduced = request.getParameter("introduced");
        String discontinued = request.getParameter("discontinued");
        String companyId = request.getParameter("companyId");

        ComputerDTO.ComputerDTOBuilder computerDTOBuilder = ComputerDTO.getComputerDtoBuilder();
        if(computerId!=null) {
            computerDTOBuilder.id(Integer.parseInt(computerId));
        }
        computerDTOBuilder.name(computerName).introduced(introduced).discontinued(discontinued);
        computerDTOBuilder.companyId(Integer.parseInt(companyId));

        return computerDTOBuilder.build();
    }
}
