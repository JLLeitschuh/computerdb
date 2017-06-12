package com.ebiz.computerdatabase.mapper;

import com.ebiz.computerdatabase.dto.CompanyDTO;
import com.ebiz.computerdatabase.model.CompanyEntity;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper {

	/**
	 *  createCompany from ResultSet.
	 * @param resultSet .
	 * @return company entity create from result set
	 */
	public static CompanyEntity createCompany(ResultSet resultSet) {

		try {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			return new CompanyEntity(id, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * create companyDTO object from companyEntity object.
	 * @param company company which will be map to DTO
	 * @return CompanyDto corresponding to company parameter
	 */
	public static CompanyDTO createCompanyDTO(CompanyEntity company) {

		return new CompanyDTO(company.getId(), company.getName());
	}

	/**
	 * create Company Object with companyDTO.
	 * @param companyDTO DTO to company entity .
	 * @return new CompanyEntity
	 */
	public static CompanyEntity createCompany(CompanyDTO companyDTO) {

		return new CompanyEntity(companyDTO.getId(), companyDTO.getName());
	}
}
