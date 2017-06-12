package com.ebiz.computerdatabase.controller;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by ebiz on 12/06/17.
 */
@Controller
public class AddComputerController {

    @Autowired
    @Qualifier("computerValidator")
    private Validator validator;

    @ModelAttribute("computer")
    public ComputerDTO createComputerDTO() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new ComputerDTO.ComputerDTOBuilder().build();
    }

    @RequestMapping(value = "/addComputer", method = RequestMethod.GET)
    public String saveEmployeePage(Model model) {

        return "addComputer";
    }
}
