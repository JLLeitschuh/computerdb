package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import dto.ComputerDTO;
import dto.ComputerDTO.ComputerDTOBuilder;
import exception.DTOException;
import mapper.ComputerMapper;
import model.ComputerEntity;
import persistance.MySQLConnectionSingleton;
import static dao.DaoUtils.*;

public class ComputerDao {

	public static final String COMPUTER_TABLE_NAME = "computer";
	Connection connect;

	public ComputerDao() {

		connect = MySQLConnectionSingleton.getInstance().getConnection();
	}

	/**
	 * find computer with specific id.
	 * @param id .
	 * @return computer entity find
	 * @throws DTOException 
	 */

	public ComputerEntity find(int id) throws DTOException {

		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
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
			throw new DTOException(e.getMessage());
		} finally {
			close(preparedStatement, resultSet, statement);
		}
		return computer;
	}

	/**
	 * create new computer into computer table.
	 * @param computer .
	 * @throws DTOException 
	 */

	public void create(ComputerEntity computer) throws DTOException {

		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = (PreparedStatement) connect
					.prepareStatement("insert into " + COMPUTER_TABLE_NAME + " values (default, ?, ?, ?, ?)");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setString(2,
					computer.getIntroduced() == null ? null : computer.getIntroduced().toString());
			preparedStatement.setString(3,
					computer.getDiscontinued() == null ? null : computer.getDiscontinued().toString());
			if (computer.getCompanyEntity() != null) {
				preparedStatement.setInt(4, computer.getCompanyEntity().getId());
			}
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DTOException(e.getMessage());

		} finally {
			close(preparedStatement, null, null);
		}

	}

	/**
	 * update computer into computer table.
	 * @param computer .
	 * @return update computer;
	 * @throws DTOException 
	 */

	public boolean update(ComputerEntity computer) throws DTOException {

		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = (PreparedStatement) connect.prepareStatement("UPDATE " + COMPUTER_TABLE_NAME
					+ " SET name = ?,introduced =?, discontinued=?,company_id=?" + " WHERE id =?");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setString(2,
					computer.getIntroduced() == null ? null : computer.getIntroduced().toString());
			preparedStatement.setString(3,
					computer.getDiscontinued() == null ? null : computer.getDiscontinued().toString());
			if (computer.getCompanyEntity() != null) {
				preparedStatement.setInt(4, computer.getCompanyEntity().getId());
			} else {
				preparedStatement.setString(4, null);
			}
			preparedStatement.setString(5, null);
			int count = preparedStatement.executeUpdate();

			return count > 0 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DTOException(e.getMessage());
		} finally {
			close(preparedStatement, null, null);
		}

	}

	/**
	 * delete computer from computer table.
	 * @param id .
	 * @throws DTOException 
	 */
	public void delete(int id) throws DTOException {

		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = (PreparedStatement) connect
					.prepareStatement("Delete From " + COMPUTER_TABLE_NAME + " WHERE id =?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DTOException(e.getMessage());
		} finally {
			close(preparedStatement, null, null);
		}

	}

	/**
	 * get number of item into computer db.
	 * @param research .
	 * @return item count into computer table
	 * @throws DTOException 
	 */
	public int getCount(String research) throws DTOException {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		StringBuilder query = new StringBuilder("SELECT Count(*) FROM " + COMPUTER_TABLE_NAME);
		if (research != null) {
			query.append(" Where name like ?");
		}
		try {
			preparedStatement = (PreparedStatement) connect.prepareStatement(query.toString());
			if (research != null) {
				preparedStatement.setString(1, "%" + research + "%");
			}
			resultSet = preparedStatement.executeQuery();
			resultSet.first();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			throw new DTOException(e.getMessage());
		} finally {
			close(preparedStatement, resultSet, null);
		}

	}

	/**
	 * display all computer details.
	 * @return computer list
	 * @throws DTOException 
	 */
	public ArrayList<ComputerEntity> getAll() throws DTOException {

		ResultSet resultSet = null;
		Statement statement;
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
			throw new DTOException(e.getMessage());
		}
		return computerList;

	}

	/**
	 * get computers on database with begin and end limit.
	 * @param start .
	 * @param offset .
	 * @param researchString .
	 * @return list of computer between begin and end
	 * @throws DTOException 
	 */
	public List<ComputerEntity> getComputers(int start, int offset, String researchString) throws DTOException {
		ArrayList<ComputerEntity> computerList = new ArrayList<>();
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		try {
			StringBuilder query = new StringBuilder("SELECT * FROM " + COMPUTER_TABLE_NAME);
			if (researchString != null) {
				query.append(" WHERE name like ?");
			}
			query.append(" Limit ?,? ");

			preparedStatement = (PreparedStatement) connect.prepareStatement(query.toString());

			if (researchString != null) {
				preparedStatement.setString(1, "%" + researchString + "%");
				preparedStatement.setInt(2, start);
				preparedStatement.setInt(3, offset);
			} else {
				preparedStatement.setInt(1, start);
				preparedStatement.setInt(2, offset);
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ComputerEntity computerEntity = ComputerMapper.createComputer(resultSet);
				computerList.add(computerEntity);
			}
		} catch (SQLException e) {
			throw new DTOException(e.getMessage());

		}

		return computerList;

	}

}
