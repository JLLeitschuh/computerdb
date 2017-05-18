package ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.StringUtils;

import exception.DTOException;
import model.ComputerEntity;
import persistance.MySQLConnectionSingleton;
import persistance.Query;
import services.CompanyService;
import services.ComputerService;

public class Menu {

	ComputerService computerService;
	CompanyService companyService;
	Scanner in;

	/**
	 * constructor.
	 */
	public Menu() {
		computerService = new ComputerService();
		companyService = new CompanyService();
		in = new Scanner(System.in);
	}

	/**
	 * make menu choice.
	 * @throws DTOException 
	 * @throws NumberFormatException 
	 */
	public void chooseMenu() throws NumberFormatException, DTOException {

		boolean keepChoose = true;
		while (keepChoose) {

			System.out.println("Display Computer List write 1 ");
			System.out.println("Display Company List write 2 ");
			System.out.println("Insert new computer write 3");
			System.out.println("Update existing computer write 4 ");
			System.out.println("Display one Computer write 5 ");
			System.out.println("Delete  Computer write 6 ");
			System.out.println("Write 7 to quit ");
			String choice = in.nextLine();

			if (StringUtils.isStrictlyNumeric(choice)) {
				if (Integer.parseInt(choice) == 7) {
					keepChoose = false;
					try {
						MySQLConnectionSingleton.getInstance().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					choice(Integer.parseInt(choice));
				}
			}

		}
		in.close();

	}

	/**
	 * choice statement with numeric selected by user.
	 * @param numeric .
	 * @throws DTOException 
	 */
	public void choice(int numeric) throws DTOException {

		switch (numeric) {

		case Query.DISPLAYCOMPANYLIST:
			displayCompanyList();
			break;
		case Query.DISPLAYCOMPUTERLIST:
			displayComputerList();
			break;
		case Query.INSERTCOMPUTER:
			insertComputer();
			break;
		case Query.UPDATECOMPUTER:
			chooseComputerToUpdate();
			break;
		case Query.DISPLAYCOMPUTERBYID:
			displayComputerById();
			break;
		case Query.DELETECOMPUTERBYID:
			deleteComputerById();
			break;

		default:
			System.out.println("Choice must be between 1 and 6, try again");
			break;
		}

	}

	/**
	 * delete computer from computer table.
	 * @throws DTOException 
	 */
	public void deleteComputerById() throws DTOException {

		System.out.println("Delete computer with id :");
		String computerId = in.nextLine();

		if (StringUtils.isStrictlyNumeric(computerId)) {

			ComputerEntity computerModel = computerService.getComputerById(computerId);
			if (computerModel != null) {

				computerService.deleteComputer(computerId);
			} else {
				System.out.println("This computer doesn't exist");
			}

		}

	}

	/**
	 * display Computer with specific id.
	 * @throws DTOException 
	 */
	public void displayComputerById() throws DTOException {

		choice(Query.DISPLAYCOMPUTERLIST);
		System.out.println("Choose Computer ID to display details ");

		String computerId = in.nextLine();
		boolean isComputerIdOk = StringUtils.isStrictlyNumeric(computerId);

		if (isComputerIdOk) {
			ComputerEntity computer = computerService.getComputerById(computerId);
			if (computer != null) {
				System.out.println(computer.toString());
			} else {
				System.out.println("Computer doesn't exist");
			}

		} else {
			System.out.println("Computer Id must be numeric");
		}
	}

	/**
	 * chooseComputerToUpdate.
	 * @throws DTOException 
	 */
	public void chooseComputerToUpdate() throws DTOException {

		choice(Query.DISPLAYCOMPUTERLIST);
		System.out.println("Choose Computer To update with id ");

		String computerId = in.nextLine();
		boolean isComputerIdOk = StringUtils.isStrictlyNumeric(computerId);

		if (isComputerIdOk) {
			updateComputer(computerId);
		} else {
			System.out.println("Computer Id must be numeric");
		}
	}

	/**
	 * update Computer with specific id.
	 * @param idComputerUpdate .
	 * @throws DTOException 
	 */
	public void updateComputer(String idComputerUpdate) throws DTOException {

		ComputerEntity computerModel = computerService.getComputerById(idComputerUpdate);
		if (computerModel != null) {
			System.out.print("Enter name:");

			String name = in.nextLine();
			System.out.print("introduced:");
			String introduced = in.nextLine();
			System.out.print("discontinued:");
			String discontinued = in.nextLine();

			System.out.print("Company ID:");
			String companyId = in.nextLine();

			computerService.update(computerModel);

		} else {
			System.out.println("Computer doesn't exist");
		}

	}

	/**
	 * insert new computer into computer table.
	 */
	public void insertComputer() {

		System.out.print("Enter name:");

		String name = in.nextLine();
		System.out.print("Enter introduced:");
		String introduced = in.nextLine();
		System.out.print("Enter discontinuited :");
		String discontinued = in.nextLine();

		System.out.print("Company ID:");
		String companyId = in.nextLine();

		//computerService.insertComputer(name, introduced, discontinued, companyId);

	}

	/**
	 * display all computers from computer table.
	 * @throws DTOException 
	 */
	public void displayComputerList() throws DTOException {
		computerService.getComputers();
	}

	/**
	 * display all company from company table.
	 */
	public void displayCompanyList() {
		companyService.getCompanies();
	}

}
