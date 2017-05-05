package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CompanyDao;
import dao.ComputerDao;
import mappers.DataMapper;
import model.ComputerEntity;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ComputerDao computerDao;
	
	CompanyDao companyDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
        computerDao = new ComputerDao();
        companyDao = new CompanyDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());		
		request.setAttribute("computerList", companyDao.getAll());
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/addComputer.jsp" ).forward( request, response );
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String computerName = request.getParameter("computerName");
		LocalDate introduced = DataMapper.convertStringToDate(request.getParameter("introduced"));
		LocalDate discontinued = DataMapper.convertStringToDate(request.getParameter("discontinued"));
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		
		ComputerEntity computerEntity = new ComputerEntity.ComputerBuilder().name(computerName).introduced(introduced).discontinued(discontinued).company(companyDao.find(companyId)).build();
		computerDao.create(computerEntity);
	}

}
