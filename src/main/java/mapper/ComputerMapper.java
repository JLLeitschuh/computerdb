package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import exception.DTOException;
import model.CompanyEntity;
import model.ComputerEntity;
import persistence.dao.CompanyDao;

public class ComputerMapper {

	/**
	 *  create computer object from result set.
	 * @param resultSet .
	 * @return computer entity from result set
	 * @throws DTOException 
	 */
	public static ComputerEntity createComputer(ResultSet resultSet) throws DTOException {

		try {
			CompanyDao companyDao = new CompanyDao();
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			LocalDate introduced = resultSet.getDate(3) == null ? null : resultSet.getDate(3).toLocalDate();
			LocalDate discontinued = resultSet.getDate(4) == null ? null : resultSet.getDate(4).toLocalDate();
			int companyId = resultSet.getInt(5);
			CompanyEntity companyEntity = companyDao.find(companyId);
			return new ComputerEntity.ComputerBuilder().id(id).name(name).introduced(introduced)
					.discontinued(discontinued).company(companyEntity).build();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
