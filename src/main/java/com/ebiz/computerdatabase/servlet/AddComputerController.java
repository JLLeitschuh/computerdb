package com.ebiz.computerdatabase.servlet;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.log.LoggerSing;
import com.ebiz.computerdatabase.mapper.CompanyDTOMapper;
import com.ebiz.computerdatabase.service.CompanyService;
import com.ebiz.computerdatabase.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@RequestMapping("/")
@Controller
public class AddComputerController{

	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;

	@Autowired
	private Validator computerValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.addValidators(computerValidator);
	}

	@RequestMapping(value = "addComputer", method = RequestMethod.GET)
	protected String getAddComputer(Locale locale,Model model) {

		LoggerSing.logger.error("LANGUAGE ="+locale);
		model.addAttribute("companyList", CompanyDTOMapper.createCompanyDtoList(companyService.getCompanies()));
		model.addAttribute("computer", new ComputerDTO());
		return "addComputer";
	}

	@RequestMapping(value = "addComputer", method = RequestMethod.POST)
	protected String addComputer(@ModelAttribute("computer") @Validated ComputerDTO computer, BindingResult bindingResult, Model model) {

		model.addAttribute("companyList", CompanyDTOMapper.createCompanyDtoList(companyService.getCompanies()));
		model.addAttribute("computer", computer);
		if (bindingResult.hasErrors()) {

			return "addComputer";
		}
		computerService.insertComputer(computer);
		return "redirect:addComputer";

	}

}
