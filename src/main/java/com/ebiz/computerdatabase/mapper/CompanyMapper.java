package com.ebiz.computerdatabase.mapper;

import com.ebiz.computerdatabase.dto.CompanyDTO;
import com.ebiz.computerdatabase.model.CompanyEntity;
import com.ebiz.computerdatabase.model.ComputerEntity;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper implements RowMapper<CompanyEntity> {

	/**
	 * create companyDTO object from companyEntity object.
	 * @param company company which will be map to DTO
	 * @return CompanyDto corresponding to company parameter
	 */
	public static CompanyDTO createCompanyDTO(CompanyEntity company) {

		return new CompanyDTO(company.getId(), company.getName());
	}


	@Override
	public CompanyEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		int id = resultSet.getInt(1);
		String name = resultSet.getString(2);
		return new CompanyEntity(id, name);

	}
}
