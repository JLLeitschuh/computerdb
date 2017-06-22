package com.ebiz.computerdatabase.servlet;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.exception.BadRequestException;
import com.ebiz.computerdatabase.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ebiz on 22/06/17.
 */
@RequestMapping(value="/api/editComputer")
@RestController
public class EditComputerRestController {

    @Autowired
    ComputerService computerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> editComputer(@RequestBody ComputerDTO computerDTO) {

        computerService.update(computerDTO);
        return new ResponseEntity("computer updated with success", HttpStatus.ACCEPTED);
    }


}
