package model;

import java.sql.Timestamp;

public class ComputerModel {

	private int id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private int companyId;
	
	
	public ComputerModel(int id, String name, Timestamp introduced, Timestamp discontinued, int companyId) {
	
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	
	public ComputerModel( String name,Timestamp introduced, Timestamp discontinued, int companyId) {
		
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	
	public ComputerModel(){
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}
	public Timestamp getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("id: "+id +",");
		strBuilder.append("name: "+this.name+ ",");
		strBuilder.append("introduced: "+this.introduced+ ",");
		strBuilder.append("discontinued: "+this.discontinued+ ",");
		strBuilder.append("companyId: "+this.companyId);
		return strBuilder.toString();
	}
	
	
}
