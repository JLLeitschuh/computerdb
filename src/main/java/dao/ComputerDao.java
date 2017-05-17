package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import dto.ComputerDTO;
import dto.ComputerDTO.ComputerDTOBuilder;
import mappers.ComputerMapper;
import model.ComputerEntity;

public class ComputerDao extends IDao<ComputerEntity> {

	public static final String COMPUTER_TABLE_NAME = "computer";

	/**
	 * find computer with specific id.
	 * @param id .
	 * @return computer entity find
	 */
	@Override
	public ComputerEntity find(int id) {

		ComputerEntity computer = null;
		try {

			preparedStatement = (PreparedStatement) connect
					.prepareStatement("SELECT * FROM " + COMPUTER_TABLE_NAME + " WHERE id =?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				computer = ComputerMapper.createComputer(resultSet);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return computer;
	}

	/**
	 * create new computer into computer table.
	 * @param computer .
	 */
	@Override
	public void create(ComputerEntity computer) {
		try {

			preparedStatement = (PreparedStatement) connect
					.prepareStatement("insert into " + COMPUTER_TABLE_NAME + " values (default, ?, ?, ?, ?)");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setString(2,
					computer.getIntroduced() == null ? null : computer.getIntroduced().toString());
			preparedStatement.setString(3,
					computer.getDiscontinued() == null ? null : computer.getDiscontinued().toString());
			preparedStatement.setInt(4, computer.getCompanyEntity().getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			close();
		}

	}

	/**
	 * update computer into computer table.
	 * @param computer .
	 * @return update computer;
	 */
	@Override
	public ComputerEntity update(ComputerEntity computer) {

		try {

			preparedStatement = (PreparedStatement) connect.prepareStatement("UPDATE " + COMPUTER_TABLE_NAME
					+ " SET name = ?,introduced =?, discontinued=?,company_id=?" + " WHERE id =?");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setString(2,
					computer.getIntroduced() == null ? null : computer.getIntroduced().toString());
			preparedStatement.setString(3,
					computer.getDiscontinued() == null ? null : computer.getDiscontinued().toString());
			preparedStatement.setInt(4, computer.getCompanyEntity().getId());
			preparedStatement.setInt(5, computer.getId());
			preparedStatement.executeUpdate();

			computer = find(computer.getId());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return computer;
	}

	/**
	 * get computer List between begin and end parameters.
	 * @param begin parameter corresponding to the start of list
	 * @param end  parameter corresponding to the end of list
	 * @return computerEntity list
	 */
	List<ComputerEntity> getComputerLimite(int begin, int end) {

		ArrayList<ComputerEntity> computerList = new ArrayList<>();
		try {

			preparedStatement = (PreparedStatement) connect
					.prepareStatement("SELECT * FROM " + COMPUTER_TABLE_NAME + " limit ?,?");
			preparedStatement.setInt(1, begin);
			preparedStatement.setInt(2, end);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ComputerEntity computerEntity = ComputerMapper.createComputer(resultSet);
				computerList.add(computerEntity);
				System.out.println("Value " + computerEntity.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computerList;

	}

	/**
	 * create ComputerDTO corresponding to computerEntity.
	 * @param computer entity.
	 * @return ComputerDTO map with parameter computer
	 */
	public ComputerDTO createComputerDTO(ComputerEntity computer) {

		ComputerDTOBuilder computerDTOBuilder = new ComputerDTOBuilder();

		if (computer != null) {
			computerDTOBuilder.id(computer.getId()).name(computer.getName());

			if (computer.getIntroduced() != null) {
				computerDTOBuilder.introduced(computer.getIntroduced().toString());
			}
			if (computer.getDiscontinued() != null) {
				computerDTOBuilder.discontinued(computer.getDiscontinued().toString());
			}
			if (computer.getCompanyEntity() != null) {

				computerDTOBuilder.companyId(computer.getCompanyEntity().getId());
				computerDTOBuilder.companyName(computer.getCompanyEntity().getName());
			}
		}

		return computerDTOBuilder.build();

	}

	/**
	 * delete computer from computer table.
	 * @param id .
	 */
	@Override
	public void delete(int id) {
		try {

			preparedStatement = (PreparedStatement) connect
					.prepareStatement("Delete From " + COMPUTER_TABLE_NAME + " WHERE id =?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

	}

	/**
	 * get number of item into computer db.
	 * @return item count into computer table
	 */
	public int getCount() {
		try {
			statement = (Statement) connect.createStatement();
			resultSet = statement.executeQuery("SELECT Count(*) FROM " + COMPUTER_TABLE_NAME);
			resultSet.first();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * get number of item into computer db.
	 * @return item count into computer table
	 */
	public int getCountResearch(String research) {
		try {
			preparedStatement = (PreparedStatement) connect
					.prepareStatement("SELECT Count(*) FROM " + COMPUTER_TABLE_NAME + " WHERE name like ?");

			preparedStatement.setString(1, research);
			resultSet = preparedStatement.executeQuery();
			resultSet.first();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * display all computer details.
	 * @return computer list
	 */
	@Override
	public ArrayList<ComputerEntity> getAll() {

		ArrayList<ComputerEntity> computerList = new ArrayList<>();
		try {
			statement = (Statement) connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM " + COMPUTER_TABLE_NAME);

			while (resultSet.next()) {

				ComputerEntity computerEntity = ComputerMapper.createComputer(resultSet);
				computerList.add(computerEntity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computerList;

	}

	/**
	 * get computers on database with begin and end limit.
	 * @param begin .
	 * @param limit .
	 * @return list of computer between begin and end
	 */
	public ArrayList<ComputerEntity> getElementWithLimits(int begin, int limit) {
		ArrayList<ComputerEntity> computerList = new ArrayList<>();
		try {
			preparedStatement = (PreparedStatement) connect
					.prepareStatement("SELECT * FROM " + COMPUTER_TABLE_NAME + " Limit ?,? ");

			preparedStatement.setInt(1, begin);
			preparedStatement.setInt(2, limit);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ComputerEntity computerEntity = ComputerMapper.createComputer(resultSet);
				computerList.add(computerEntity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return computerList;

	}

	/**
	 * get computers on database with begin end limit and research .
	 * @param begin .
	 * @param limit .
	 * @param strSearchElement .
	 * @return list of computer between begin and end
	 */
	public ArrayList<ComputerEntity> getSearchElementWithLimits(int begin, int limit, String strSearchElement) {
		ArrayList<ComputerEntity> computerList = new ArrayList<>();
		try {
			preparedStatement = (PreparedStatement) connect
					.prepareStatement("SELECT * FROM " + COMPUTER_TABLE_NAME + " WHERE name like ? Limit ?,? ");

			preparedStatement.setString(1, "%" + strSearchElement + "%");
			preparedStatement.setInt(2, begin);
			preparedStatement.setInt(3, limit);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ComputerEntity computerEntity = ComputerMapper.createComputer(resultSet);
				computerList.add(computerEntity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return computerList;

	}

}
