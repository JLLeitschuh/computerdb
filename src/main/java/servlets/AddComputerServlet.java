package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import dto.ComputerDTO;
import dto.ComputerDTO.ComputerDTOBuilder;
import exception.DTOException;
import mappers.ComputerDTOMapper;
import mappers.ComputerMapper;
import services.CompanyService;
import services.ComputerService;

@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ComputerService computerService;
	CompanyService companyService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputerServlet() {
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setAttribute("companyList", companyService.getCompanies());

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
		if (companyId != null && StringUtils.isNumeric(companyId)) {
			computerDTOBuilder.companyId(Integer.parseInt(companyId));
		}
		try {
			computerService.insertComputer(ComputerDTOMapper.createComputer(computerDTOBuilder.build()));
		} catch (DTOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
