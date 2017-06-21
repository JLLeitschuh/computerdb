package com.ebiz.computerdatabase.servlet;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.entities.Computer;
import com.ebiz.computerdatabase.log.LoggerSing;
import com.ebiz.computerdatabase.mapper.ComputerDTOMapper;

import com.ebiz.computerdatabase.mapper.PageRequestMapper;
import com.ebiz.computerdatabase.model.PageItem;
import com.ebiz.computerdatabase.service.CompanyService;
import com.ebiz.computerdatabase.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

		PageRequest pageRequest = PageRequestMapper.pageRequestMapper(requestParams);

		Page<Computer> page = computerService.getPage(pageRequest,requestParams.get("search"));
		PageItem pageItem = new PageItem(page, ComputerDTOMapper.createComputerDTOList(page.getContent()),requestParams);
		model.addAttribute("page", pageItem);
		model.addAttribute("companyList", companyService.getCompanies());

		return "dashboard";
	}

	@RequestMapping(value = "dashboard", method = RequestMethod.POST)
	public RedirectView deleteComputer(@RequestParam Map<String,String> requestParams){

		List<String> selectedComputers = Arrays.asList(requestParams.get("selection").split(","));
 		List<Integer> listInteger = selectedComputers.stream().map(Integer::valueOf).collect(Collectors.toList());
 		 listInteger.stream().forEach(a->LoggerSing.logger.error("Integer to delete "+a));

		computerService.deleteComputer(selectedComputers.stream().map(Integer::valueOf).collect(Collectors.toList()));
		return new RedirectView("dashboard");

	}



}
