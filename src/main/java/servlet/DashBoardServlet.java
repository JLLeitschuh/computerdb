package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import dto.ComputerDTO;
import exception.DTOException;
import mapper.ComputerDTOMapper;
import model.ComputerEntity;
import service.ComputerService;
import ui.Page;
import static log.LoggerSing.getLog;

/**
 * Servlet implementation class DashBoardServlet.
 */
@WebServlet("/dashboard")
public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ComputerService computerService;
	Page<ComputerDTO> pageComputer;
	Logger logger;

	/**
	 * @throws DTOException 
	 * @see HttpServlet#HttpServlet()
	 */
	public DashBoardServlet() throws DTOException {
		super();
		computerService = new ComputerService();
		this.pageComputer = new Page<ComputerDTO>();
		logger = org.slf4j.LoggerFactory.getLogger(DashBoardServlet.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param request .
	 * @param response .
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get parameters search, page, item_number
		String searchString = request.getParameter("search");
		String page = request.getParameter("page");
		String itemNumber = request.getParameter("item_number");
		String orderBy = request.getParameter("orderby");
		String order = request.getParameter("order");

		logger.info("GET " + pageComputer.getCurrentPage());

		// get computer list with research param, if research is null all items
		// are send by database.
		try {
			pageComputer.setNumberTotalItems(computerService.getTotalItem(searchString));
		} catch (DTOException e) {

			logger.error(e.getMessage());
		}

		logger.info("TotalItems " + pageComputer.getNumberTotalItems());

		// check if page is not null and numeric to update currentPage
		if (page != null && StringUtils.isNumeric(page)) {
			pageComputer.setCurrentPage(Integer.parseInt(page));
		}
		if (page == null) {
			pageComputer.setCurrentPage(1);
		}

		int start = 0;
		// check if itemNumber is not null and numeric, if it's not itemNumber
		// is 10 by default
		if (itemNumber != null && StringUtils.isNumeric(itemNumber)) {
			pageComputer.updateNumberPage(Integer.parseInt(itemNumber));
			start = (pageComputer.getCurrentPage() - 1) * Integer.parseInt(itemNumber);
		} else {
			pageComputer.updateNumberPage(10);
			start = (pageComputer.getCurrentPage() - 1) * 10;
		}

		if (order == null) {
			order = "0";
		}

		getLog().logInfo("OrderBy " + orderBy + " order " + order);

		List<ComputerEntity> computerList = null;
		try {
			computerList = computerService.getComputers(start, itemNumber, searchString, orderBy,
					Integer.parseInt(order));
			if (computerList == null || pageComputer.getCurrentPage() < 0
					|| (pageComputer.getCurrentPage() > pageComputer.getNumberPage())) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
			} else {

				pageComputer.setItems(ComputerDTOMapper.createComputerDTOList(computerList));
				request.setAttribute("search", searchString);
				request.setAttribute("page", pageComputer);
				request.setAttribute("orderby", orderBy);
				request.setAttribute("order", order);
				request.setAttribute("item_number", itemNumber);
				request.setAttribute("computerList", computerList);
				this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
			}
		} catch (DTOException e) {

			logger.error(e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param request .
	 * @param response .
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] selectedComputers = request.getParameter("selection").split(",");

		try {
			computerService.deleteComputer(selectedComputers);
			doGet(request, response);

		} catch (DTOException e) {
			logger.error(e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/500.jsp").forward(request, response);
		}

	}

}
