package com.ebiz.computerdatabase.servlet;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.log.LoggerSing;
import com.ebiz.computerdatabase.model.Page;
import com.ebiz.computerdatabase.model.PageRequest;
import com.ebiz.computerdatabase.service.CompanyService;
import com.ebiz.computerdatabase.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


import java.util.Map;


/**
 * Servlet implementation class DashBoardServlet.
 */


@RequestMapping("/")
@Controller
public class DashboardController {

	private static final long serialVersionUID = 1L;


	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;


	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String getDashBoard(Model model, @RequestParam Map<String,String> requestParams){

		PageRequest pageRequest = new PageRequest(requestParams);
		Page<ComputerDTO> page = computerService.getPage(pageRequest);
		model.addAttribute("page", page);
		model.addAttribute("companyList", companyService.getCompanies());

		return "dashboard";
	}

	@RequestMapping(value = "dashboard", method = RequestMethod.POST)
	public RedirectView deleteComputer(@RequestParam Map<String,String> requestParams){
		String[] selectedComputers = requestParams.get("selection").split(",");
		computerService.deleteComputer(selectedComputers);
		return new RedirectView("dashboard");

	}



}
