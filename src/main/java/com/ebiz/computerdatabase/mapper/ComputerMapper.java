package com.ebiz.computerdatabase.mapper;

import com.ebiz.computerdatabase.model.CompanyEntity;
import com.ebiz.computerdatabase.model.ComputerEntity;
import com.ebiz.computerdatabase.persistence.dao.CompanyDao;
import com.ebiz.computerdatabase.exception.DTOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component("computerMapper")
public class ComputerMapper {


	@Autowired
	public CompanyDao companyDao ;

	public ComputerEntity createComputer(ResultSet resultSet) throws DTOException {

		try {

			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			LocalDate introduced = resultSet.getDate(3) == null ? null : resultSet.getDate(3).toLocalDate();
			LocalDate discontinued = resultSet.getDate(4) == null ? null : resultSet.getDate(4).toLocalDate();
			int companyId = resultSet.getInt(5);
			CompanyEntity companyEntity = companyDao.find(companyId);
			return new ComputerEntity.ComputerBuilder().id(id).name(name).introduced(introduced)
					.discontinued(discontinued).company(companyEntity).build();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

}
