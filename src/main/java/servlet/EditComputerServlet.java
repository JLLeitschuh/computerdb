package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ComputerDTO;
import dto.ComputerDTO.ComputerDTOBuilder;
import exception.DTOException;
import mapper.ComputerDTOMapper;
import model.CompanyEntity;
import service.CompanyService;
import service.ComputerService;

/**
 * Servlet implementation class EditComputerServlet.
 */
@WebServlet("/editComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerService computerService;
	private CompanyService companyService;
	Logger logger;

	/**
	 * @throws DTOException .
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputerServlet() throws DTOException {
		super();
		computerService = ComputerService.getComputerService();
		companyService = CompanyService.getCompanyService();
		logger = LoggerFactory.getLogger(DashboardServlet.class);
		// TODO Auto-generated constructor stub
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

		String computerId = request.getParameter("computerId");
		ComputerDTO computer = null;
		try {
			computer = ComputerDTOMapper.createComputerDTO(computerService.getComputerById(computerId));
		} catch (DTOException e) {

			logger.error(e.getMessage());
		}

		request.setAttribute("computerId", computerId);

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



		boolean success = computerService.update(request);
		logger.info("success " + success);
		// test if update is successfull . If it's not, send error 500
		if (!success) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
		} else {
			doGet(request, response);
		}

	}

}
