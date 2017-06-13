package com.ebiz.computerdatabase.servlet;


import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.log.LoggerSing;
import com.ebiz.computerdatabase.service.CompanyService;
import com.ebiz.computerdatabase.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



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
	protected String getAddComputer(Model model) {

		model.addAttribute("companyList", companyService.getCompanies());
		model.addAttribute("computer", new ComputerDTO());
		return "addComputer";
	}

	@RequestMapping(value = "addComputer", method = RequestMethod.POST)
	protected String addComputer(@Validated ComputerDTO computerDTO, BindingResult bindingResult,Model model) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("computer",computerDTO);
			return "404";
		}
		computerService.insertComputer(computerDTO);
		model.addAttribute("companyList", companyService.getCompanies());
		 return "addComputer";

	}

}