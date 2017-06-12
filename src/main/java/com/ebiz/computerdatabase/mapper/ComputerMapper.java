package com.ebiz.computerdatabase.mapper;


import com.ebiz.computerdatabase.model.ComputerEntity;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class ComputerMapper implements RowMapper<ComputerEntity> {

	@Override
	public ComputerEntity mapRow(ResultSet resultSet, int rowNum) throws  SQLException{


		int id = resultSet.getInt(1);
		String name = resultSet.getString(2);
		LocalDate introduced = resultSet.getDate(3) == null ? null : resultSet.getDate(3).toLocalDate();
		LocalDate discontinued = resultSet.getDate(4) == null ? null : resultSet.getDate(4).toLocalDate();
		int companyId = resultSet.getInt(5);
		String companyName = resultSet.getString(7);
		return new ComputerEntity.ComputerBuilder().id(id).name(name).introduced(introduced)
				.discontinued(discontinued).companyId(companyId).companyName(companyName).build();

	}
}
