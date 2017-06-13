package com.ebiz.computerdatabase.servlet;


import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.mapper.RequestMapper;
import com.ebiz.computerdatabase.service.CompanyService;
import com.ebiz.computerdatabase.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;




@RequestMapping("/")
@Controller
public class AddComputerController{

	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "addComputer", method = RequestMethod.GET)
	protected String getAddComputer(Model model) {

		model.addAttribute("companyList", companyService.getCompanies());
		return "addComputer";
	}

	@RequestMapping(value = "addComputer", method = RequestMethod.POST)
	protected RedirectView addComputer(ComputerDTO computerDTO) {

		computerService.insertComputer(computerDTO);
		 return new RedirectView("addComputer");

	}

}
