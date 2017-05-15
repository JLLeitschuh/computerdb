package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import dao.CompanyDao;
import dao.IDao;
import model.CompanyEntity;
import model.ComputerEntity;

public class ComputerMapper {

	/**
	 *  create computer object from result set.
	 * @param resultSet .
	 * @return computer entity from result set
	 */
	public static ComputerEntity createComputer(ResultSet resultSet) {

		IDao<CompanyEntity> companydao = new CompanyDao();
		try {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			LocalDate introduced = resultSet.getDate(3) == null ? null : resultSet.getDate(3).toLocalDate();
			LocalDate discontinued = resultSet.getDate(4) == null ? null : resultSet.getDate(4).toLocalDate();
			int companyId = resultSet.getInt(5);
			CompanyEntity companyEntity = companydao.find(companyId);
			return new ComputerEntity.ComputerBuilder().id(id).name(name).introduced(introduced)
					.discontinued(discontinued).company(companyEntity).build();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
