package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dto.ComputerDTO;
import exception.DTOException;
import log.LoggerSing;
import model.Page;
import model.PageRequest;
import service.ComputerService;


/**
 * Servlet implementation class DashBoardServlet.
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ComputerService computerService;
	LoggerSing logger = new LoggerSing(this.getClass());

	/**
	 * @throws DTOException .
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() throws DTOException {
		super();
		computerService = ComputerService.getComputerService();

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
