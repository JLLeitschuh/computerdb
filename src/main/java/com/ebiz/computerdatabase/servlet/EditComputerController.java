package com.ebiz.computerdatabase.servlet;

import com.ebiz.computerdatabase.dto.ComputerDTO;

import com.ebiz.computerdatabase.mapper.ComputerDTOMapper;
import com.ebiz.computerdatabase.service.CompanyService;
import com.ebiz.computerdatabase.service.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.view.RedirectView;


import java.util.Map;

@RequestMapping("/")
@Controller
public class EditComputerController {


	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;

	Logger logger = LoggerFactory.getLogger(this.getClass());;

	@RequestMapping(value = "editComputer", method = RequestMethod.GET)
	protected String getEditComputer(Model model,@RequestParam Map<String,String> requestParams) {


		String computerId = requestParams.get("id");
		ComputerDTO computer = ComputerDTOMapper.createComputerDTO(computerService.getComputerById(computerId));

		model.addAttribute("id", computerId);

		if (computer == null) {
			return "400";
		} else {

			model.addAttribute("companyList", companyService.getCompanies());
			model.addAttribute("computer", computer);
			return "editComputer";

		}

	}

	@RequestMapping(value = "editComputer", method = RequestMethod.POST)
	public RedirectView editComputer(ComputerDTO computerDTO){

		boolean success = computerService.update(computerDTO);

		if (!success) {
			return new RedirectView("400");
		} else {
			return new RedirectView("editComputer");
		}

	}



}
