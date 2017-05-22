package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import dto.ComputerDTO;
import dto.ComputerDTO.ComputerDTOBuilder;
import exception.DTOException;
import log.LoggerSing;
import mapper.ComputerDTOMapper;
import mapper.ComputerMapper;
import model.CompanyEntity;
import service.CompanyService;
import service.ComputerService;

@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ComputerService computerService;
	CompanyService companyService;

	/**
	 * @throws DTOException 
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputerServlet() throws DTOException {
		super();
		computerService = new ComputerService();
		companyService = new CompanyService();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param request .
	 * @param response .
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("companyList", companyService.getCompanies());
		} catch (DTOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
	}

	/**
	 * doPost.
	 * @param request .
	 * @param response .
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");

		ComputerDTOBuilder computerDTOBuilder = ComputerDTO.getComputerDtoBuilder();
		computerDTOBuilder.name(computerName).introduced(introduced).discontinued(discontinued);

		CompanyEntity company = null;

		try {
			company = companyService.findCompanyById(companyId);
		} catch (DTOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (company != null) {
			computerDTOBuilder.companyId(company.getId());
			computerDTOBuilder.companyName(company.getName());
		}

		try {
			computerService.insertComputer(ComputerDTOMapper.createComputer(computerDTOBuilder.build()));
			doGet(request, response);
		} catch (DTOException e) {
			LoggerSing.getLog().logError(e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
		} catch (NullPointerException e) {
			LoggerSing.getLog().logError(e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
		}

	}

}
