package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.StringUtils;

import dto.ComputerDTO;
import dto.ComputerDTO.ComputerDTOBuilder;
import exception.DTOException;
import mapper.ComputerDTOMapper;
import mapper.ComputerMapper;
import model.CompanyEntity;
import model.ComputerEntity;
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
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputerServlet() {
		super();
		computerService = new ComputerService();
		companyService = new CompanyService();
		logger = LoggerFactory.getLogger(DashBoardServlet.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param request .
	 * @param response .
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String computerId = request.getParameter("computerId");
		ComputerDTO computer = null;
		try {
			computer = ComputerDTOMapper.createComputerDTO(computerService.getComputerById(computerId));
		} catch (DTOException e1) {

			e1.printStackTrace();
		}

		request.setAttribute("computerId", computerId);

		// If computer doesn't exist, error is send
		if (computer == null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
		} else {
			try {
				request.setAttribute("companyList", companyService.getCompanies());
			} catch (DTOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("computer", computer);
			this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param request .
	 * @param response .
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String computerId = request.getParameter("computerId");
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");

		logger.info("computerId " + computerId);

		ComputerDTOBuilder computerDTOBuilder = ComputerDTO.getComputerDtoBuilder();
		if (computerId != null && StringUtils.isStrictlyNumeric(computerId)) {

			computerDTOBuilder.id(Integer.parseInt(computerId));
		}
		computerDTOBuilder.name(computerName).introduced(introduced).discontinued(discontinued);

		CompanyEntity company = null;

		try {
			company = companyService.findCompanyById(companyId);
		} catch (DTOException e) {

			e.printStackTrace();
		}
		if (company != null) {
			computerDTOBuilder.companyId(company.getId());
			computerDTOBuilder.companyName(company.getName());
		}

		try {
			boolean success = computerService.update(ComputerDTOMapper.createComputer(computerDTOBuilder.build()));
			if (success) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
			} else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
			}
		} catch (DTOException e) {

			this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
		}

	}

}
