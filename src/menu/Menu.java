package menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.mysql.jdbc.StringUtils;

import model.ComputerModel;

public class Menu {

	
	public static String convertToDate(String date){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here	
		java.util.Date dateStr;
		
		
		try {
			dateStr = formatter.parse(date);
			java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
			
			return dateDB.toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return "";
			
		}
		
	}
	
	public static void updateComputer(int idComputerUpdate){
		
		
		ComputerModel computerModel = null;
		System.out.print("Enter name:");
		Scanner in = new Scanner(System.in);
		String name =in.nextLine();
		System.out.print("introduced:");
		String introduced =convertToDate(in.nextLine());
		System.out.print("introduced:");
		String discontinued =convertToDate(in.nextLine());
		
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
		
		while(StringUtils.isStrictlyNumeric(companyId)){
			System.out.print("Company ID:");
			companyId =in.nextLine();
		}
		
		computerModel.setCompanyId(Integer.parseInt(companyId));
		
		
	}
	
	
}
