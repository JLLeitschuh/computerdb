package menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.mysql.jdbc.StringUtils;

import Utils.Utils;
import computerdb.MySQLConnectDB;
import computerdb.Query;
import model.ComputerModel;

public class Menu {

	MySQLConnectDB mySQLDb;
	Scanner in;

	public Menu(){
		this.mySQLDb = new MySQLConnectDB();
		in = new Scanner(System.in);
	}


	public void chooseMenu(){


		boolean keepChoose = true;
		while(keepChoose){

			System.out.println("Display Computer List write 1 ");
			System.out.println("Display Company List write 2 ");
			System.out.println("Insert new computer write 3");
			System.out.println("Update existing computer write 4 ");
			System.out.println("Display one Computer write 5 ");
			System.out.println("Write 6 to quit ");
			String choice  =in.nextLine();

			if(StringUtils.isStrictlyNumeric(choice)){
				if(Integer.parseInt(choice) ==6){
					keepChoose = false;
				}else{
					choice(Integer.parseInt(choice));
				}
			}

		}
		in.close();

	}

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

		default:
			System.out.println("Choice must be between 1 and 6, try again");
			break;
		}


	}


	public void displayComputerById(){
		
		choice(Query.DISPLAYCOMPUTERLIST);
		System.out.println("Choose Computer To display with id ");
		String computerId = in.nextLine();
		boolean isComputerIdOk = StringUtils.isStrictlyNumeric(computerId);

		if(isComputerIdOk){
			mySQLDb.displayComputerById(Integer.parseInt(computerId));
		}else{
			System.out.println("Computer Id must be numeric");
		}
	}





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

	public void updateComputer(int idComputerUpdate){


		ComputerModel computerModel = mySQLDb.getComputerById(idComputerUpdate);
		if( computerModel!=null ){
			System.out.print("Enter name:");
			String name =in.nextLine();
			System.out.print("introduced:");
			String introduced =Utils.convertToDate(in.nextLine());
			System.out.print("introduced:");
			String discontinued =Utils.convertToDate(in.nextLine());

			if(!name.equalsIgnoreCase("")){
				computerModel.setName(name);
			}
			if(introduced.equalsIgnoreCase("")){
				computerModel.setIntroduced(introduced);
			}
			if(discontinued.equalsIgnoreCase("")){
				computerModel.setDiscontinued(discontinued);
			}
			System.out.print("Company ID:");
			String companyId =in.nextLine();

			boolean isCompanyIdOk = StringUtils.isStrictlyNumeric(companyId) && mySQLDb.getCompanyById(Integer.parseInt(companyId));

			if(isCompanyIdOk){
				computerModel.setCompanyId(Integer.parseInt(companyId));
			}

		}else{
			System.out.println("Computer doesn't exist");
		}

	}

	public  void insertComputer(){

		System.out.print("Enter name:");

		ComputerModel computerModel = new ComputerModel();
		String name =in.nextLine();
		System.out.print("Enter introduced:");
		String introduced =Utils.convertToDate(in.nextLine());
		System.out.print("Enter discontinuited :");
		String discontinued =Utils.convertToDate(in.nextLine());

		if(!name.equalsIgnoreCase("")){
			computerModel.setName(null);
		}
		if(introduced.equalsIgnoreCase("")){
			computerModel.setIntroduced(null);
		}
		if(discontinued.equalsIgnoreCase("")){
			computerModel.setDiscontinued(null);
		}
		System.out.print("Company ID:");
		String companyId =in.nextLine();

		boolean isCompanyIdOk = StringUtils.isStrictlyNumeric(companyId) && mySQLDb.getCompanyById(Integer.parseInt(companyId));

		while(!isCompanyIdOk){
			System.out.print("Company id must be numeric and must be into database :");
			companyId =in.nextLine();
			isCompanyIdOk = StringUtils.isStrictlyNumeric(companyId) && mySQLDb.getCompanyById(Integer.parseInt(companyId));
		}

		computerModel.setCompanyId(Integer.parseInt(companyId));
		mySQLDb.insertComputer(computerModel);

	}


	public void displayComputerList(){
		mySQLDb.displayTableData(MySQLConnectDB.COMPUTER_TABLE_NAME);
	}

	public void displayCompanyList(){
		mySQLDb.displayTableData(MySQLConnectDB.COMPANY_TABLE_NAME);
	}

}
