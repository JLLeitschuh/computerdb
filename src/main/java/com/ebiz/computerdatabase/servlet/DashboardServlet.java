package com.ebiz.computerdatabase.servlet;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.exception.DTOException;
import com.ebiz.computerdatabase.model.Page;
import com.ebiz.computerdatabase.model.PageRequest;
import com.ebiz.computerdatabase.service.ComputerService;
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
 * Servlet implementation class DashBoardServlet.
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Autowired
	private ComputerService computerService;

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

		PageRequest pageRequest = new PageRequest(request);
		Page<ComputerDTO> page = computerService.getPage(pageRequest);

		// Test not valid result
		if (page.getItems() == null || page.getPageRequest().getPage() < 0
				|| (page.getPageRequest().getPage() > page.getNumberPage())) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
		} else {

			// set all attribute and send it to the jsp
			request.setAttribute("page", page);
			this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param request .
	 * @param response .
	 *  @throws ServletException .
	 * @throws IOException .
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// delete all selected computer.
		String[] selectedComputers = request.getParameter("selection").split(",");
		computerService.deleteComputer(selectedComputers);
		doGet(request, response);

	}

}
