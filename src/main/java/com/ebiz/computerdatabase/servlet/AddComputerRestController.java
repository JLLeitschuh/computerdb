package com.ebiz.computerdatabase.servlet;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.exception.BadRequestException;
import com.ebiz.computerdatabase.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ebiz on 22/06/17.
 */
@RequestMapping(value="/api/addComputer")
@RestController
public class AddComputerRestController {

    @Autowired
    ComputerService computerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insertComputer(@RequestBody ComputerDTO computerDTO) {

        computerService.insertComputer(computerDTO);
        return new ResponseEntity("computer insert with success", HttpStatus.CREATED);
    }

}
