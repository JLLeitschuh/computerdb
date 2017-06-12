package com.ebiz.computerdatabase.servlet;

import com.ebiz.computerdatabase.dto.ComputerDTO;

import com.ebiz.computerdatabase.mapper.ComputerDTOMapper;
import com.ebiz.computerdatabase.mapper.RequestMapper;
import com.ebiz.computerdatabase.service.CompanyService;
import com.ebiz.computerdatabase.service.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class EditComputerServlet.
 */
@WebServlet("/editComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;

	Logger logger = LoggerFactory.getLogger(this.getClass());;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param request .
	 * @param response .
	 * @throws ServletException .
	 * @throws IOException .
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String computerId = request.getParameter("id");
		ComputerDTO computer = null;

		logger.info("ID COMPUTER " + computerId);
		computer = ComputerDTOMapper.createComputerDTO(computerService.getComputerById(computerId));

		request.setAttribute("id", computerId);

		// If computer doesn't exist, error is send
		if (computer == null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
		} else {

			request.setAttribute("companyList", companyService.getCompanies());
			request.setAttribute("computer", computer);
			this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param request .
	 * @param response .
	 * @throws ServletException .
	 * @throws IOException .
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean success = computerService.update(RequestMapper.requestToDTO(request));
		logger.info("success " + success);
		// test if update is successfull . If it's not, send error 500
		if (!success) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
		} else {
			doGet(request, response);
		}

	}

}
