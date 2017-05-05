package ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import com.mysql.jdbc.StringUtils;

import dao.CompanyDao;
import dao.ComputerDao;
import dao.IDao;
import mappers.DataMapper;
import model.CompanyEntity;
import model.ComputerEntity;
import persistance.MySQLConnectionSingleton;
import persistance.Query;

public class Menu {

	IDao<ComputerEntity> computerDAO ;
	IDao<CompanyEntity> companyDAO;
	Scanner in;

	public Menu(){
		computerDAO = new ComputerDao();
		companyDAO = new CompanyDao();
		in = new Scanner(System.in);
	}

	/**
	 * make menu choice 
	 */
	public void chooseMenu(){


		boolean keepChoose = true;
		while(keepChoose){

			System.out.println("Display Computer List write 1 ");
			System.out.println("Display Company List write 2 ");
			System.out.println("Insert new computer write 3");
			System.out.println("Update existing computer write 4 ");
			System.out.println("Display one Computer write 5 ");
			System.out.println("Delete  Computer write 6 ");
			System.out.println("Write 7 to quit ");
			String choice  =in.nextLine();

			if(StringUtils.isStrictlyNumeric(choice)){
				if(Integer.parseInt(choice) ==7){
					keepChoose = false;
					try {
						MySQLConnectionSingleton.getInstance().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
				}else{
					choice(Integer.parseInt(choice));
				}
			}

		}
		in.close();

	}

	/**
	 * choice statement with numeric selected by user
	 * @param numeric
	 */
	public void choice(int numeric){

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
	 * delete computer from computer table
	 */
	public void deleteComputerById() {

		System.out.println("Delete computer with id :");
		String computerId= in.nextLine();
		
		if (StringUtils.isStrictlyNumeric(computerId)) {
			
			ComputerEntity computerModel = computerDAO.find(Integer.parseInt(computerId));
			if (computerModel!=null) {
				
				computerDAO.delete(computerModel);
			}else {
				System.out.println("This computer doesn't exist");
			}

		}

	}
	
	/**
	 * display Computer with specific id
	 */
	public void displayComputerById() {

		choice(Query.DISPLAYCOMPUTERLIST);
		System.out.println("Choose Computer ID to display details ");
		
		String computerId = in.nextLine();
		boolean isComputerIdOk = StringUtils.isStrictlyNumeric(computerId);

		if (isComputerIdOk) {
			ComputerEntity computer = computerDAO.find(Integer.parseInt(computerId));
			if (computer!=null) {
				System.out.println(computer.toString());
			}else {
				System.out.println("Computer doesn't exist");
			}
			
		}else{
			System.out.println("Computer Id must be numeric");
		}
	}




	/**
	 * chooseComputerToUpdate
	 */
	public void chooseComputerToUpdate(){

		choice(Query.DISPLAYCOMPUTERLIST);
		System.out.println("Choose Computer To update with id ");

		String computerId = in.nextLine();
		boolean isComputerIdOk = StringUtils.isStrictlyNumeric(computerId) ;

		if(isComputerIdOk){
			updateComputer(Integer.parseInt(computerId));
		}else{
			System.out.println("Computer Id must be numeric");
		}
	}

	/**
	 * update Computer with specific id
	 * @param idComputerUpdate
	 */
	public void updateComputer(int idComputerUpdate){


		ComputerEntity computerModel = computerDAO.find(idComputerUpdate);
		if( computerModel!=null ){
			System.out.print("Enter name:");
			String name =in.nextLine();
			System.out.print("introduced:");
			LocalDate introduced =DataMapper.convertStringToDate(in.nextLine());
			System.out.print("discontinued:");
			LocalDate discontinued =DataMapper.convertStringToDate(in.nextLine());

			if(!name.equalsIgnoreCase("")){
				computerModel.setName(name);
			}
			if(introduced!=null){
				computerModel.setIntroduced(introduced);
			}
			if(discontinued!=null){
				computerModel.setDiscontinued(discontinued);
			}
			System.out.print("Company ID:");
			String companyId =in.nextLine();

			

			if(StringUtils.isStrictlyNumeric(companyId)){
				
				CompanyEntity companyModel = companyDAO.find(Integer.parseInt(companyId));
				if(companyModel!=null){
					computerModel.setCompanyEntity(companyModel);
				}
				
			}
			computerDAO.update(computerModel);

		}else{
			System.out.println("Computer doesn't exist");
		}

	}

	/**
	 * insert new computer into computer table
	 */
	public  void insertComputer(){

		System.out.print("Enter name:");

		ComputerEntity.ComputerBuilder computerBuilder = new ComputerEntity.ComputerBuilder();
		String name =in.nextLine();
		System.out.print("Enter introduced:");
		LocalDate introduced =DataMapper.convertStringToDate(in.nextLine());
		System.out.print("Enter discontinuited :");
		LocalDate discontinued =DataMapper.convertStringToDate(in.nextLine());

		if(name.equalsIgnoreCase("")){
			computerBuilder.name(null);
		}else{
			computerBuilder.name(name);
		}

		computerBuilder.introduced(introduced);
		computerBuilder.discontinued(discontinued);

		System.out.print("Company ID:");
		String companyId =in.nextLine();

		boolean isCompanyIdOk = StringUtils.isStrictlyNumeric(companyId) && companyDAO.find(Integer.parseInt(companyId))!=null;

		while(!isCompanyIdOk){
			System.out.print("Company id must be numeric and must be into database :");
			companyId =in.nextLine();
			isCompanyIdOk = StringUtils.isStrictlyNumeric(companyId) && companyDAO.find(Integer.parseInt(companyId))!=null;
		}

		CompanyEntity company = companyDAO.find(Integer.parseInt(companyId));
		computerBuilder.company(company);
		computerDAO.create(new ComputerEntity(computerBuilder));

	}

    /**
     * display all computers from computer table
     */
	public void displayComputerList(){
		computerDAO.getAll();
	}

	/**
	 * display all company from company table
	 */
	public void displayCompanyList(){
		companyDAO.getAll();
	}

}
