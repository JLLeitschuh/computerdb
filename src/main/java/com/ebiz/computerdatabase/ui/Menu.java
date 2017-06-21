package com.ebiz.computerdatabase.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.ebiz.computerdatabase.log.LoggerSing;
import org.apache.commons.lang3.StringUtils;

import com.ebiz.computerdatabase.exception.DAOException;
import com.ebiz.computerdatabase.model.CompanyEntity;
import com.ebiz.computerdatabase.model.ComputerEntity;
import com.ebiz.computerdatabase.persistence.Query;
import com.ebiz.computerdatabase.service.CompanyService;
import com.ebiz.computerdatabase.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Menu {

	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	Scanner in;

	/**
	 * constructor.
	 * @throws DAOException 
	 */
	public Menu() throws DAOException {

		in = new Scanner(System.in);
	}

	/**
	 * make menu choice.
	 * @throws DAOException
	 * @throws NumberFormatException 
	 */
	public void chooseMenu() throws NumberFormatException, DAOException {

		boolean keepChoose = true;
		while (keepChoose) {

			System.out.println("Display Computer List write 1 ");
			System.out.println("Display Company List write 2 ");
			System.out.println("Insert new computer write 3");
			System.out.println("Update existing computer write 4 ");
			System.out.println("Display one Computer write 5 ");
			System.out.println("Delete  Computer write 6 ");
			System.out.println("Delete  Company write 7 ");
			System.out.println("Write 8 to quit ");
			String choice = in.nextLine();

			if (StringUtils.isNumeric(choice)) {
				if (Integer.parseInt(choice) == 8) {
					keepChoose = false;

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
	 * @throws DAOException 
	 */
	public void choice(int numeric) throws DAOException {

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
		case Query.DELETECOMPANYBYID:
			deleteCompanyById();
			break;

		default:
			System.out.println("Choice must be between 1 and 6, try again");
			break;
		}

	}

	/**
	 * delete computer from computer table.
	 * @throws DAOException 
	 */
	public void deleteComputerById() throws DAOException {

		System.out.println("Delete computer with id :");
		String computerId = in.nextLine();

		if (StringUtils.isNumeric(computerId)) {

			ComputerEntity computerModel = computerService.getComputerById(computerId);
			if (computerModel != null) {
				String[] computers = new String[] { computerId };
				//computerService.deleteComputer(computers);
			} else {
				System.out.println("This computer doesn't exist");
			}

		}

	}

	/**
	 * delete company from computer table.
	 * @throws DAOException .
	 */
	public void deleteCompanyById() throws DAOException {

		System.out.println("Delete company with id :");
		String companyId = in.nextLine();

		if (StringUtils.isNumeric(companyId)) {

			companyService.deleteCompanyId(companyId);

		}

	}

	/**
	 * display Computer with specific id.
	 * @throws DAOException .
	 */
	public void displayComputerById() throws DAOException {

		choice(Query.DISPLAYCOMPUTERLIST);
		System.out.println("Choose Computer ID to display details ");

		String computerId = in.nextLine();
		boolean isComputerIdOk = StringUtils.isNumeric(computerId);

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
	 * @throws DAOException 
	 */
	public void chooseComputerToUpdate() throws DAOException {

		choice(Query.DISPLAYCOMPUTERLIST);
		System.out.println("Choose Computer To update with id ");

		String computerId = in.nextLine();
		boolean isComputerIdOk = StringUtils.isNumeric(computerId);

		if (isComputerIdOk) {
			updateComputer(computerId);
		} else {
			System.out.println("Computer Id must be numeric");
		}
	}

	/**
	 * update Computer with specific id.
	 * @param idComputerUpdate .
	 * @throws DAOException 
	 */
	public void updateComputer(String idComputerUpdate) throws DAOException {

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

		// computerService.insertComputer(name, introduced, discontinued,
		// companyId);

	}

	/**
	 * display all computers from computer table.
	 * @throws DAOException .
	 */
	public void displayComputerList()  {
		LoggerSing.logger.error("List size "+computerService.getComputers());
	}

	/**
	 * display all company from company table.
	 * @throws DAOException .
	 */
	public void displayCompanyList() throws DAOException {
		companyService.getCompanies();
	}

}
