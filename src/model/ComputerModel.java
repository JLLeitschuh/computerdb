package model;

import java.sql.Timestamp;

public class ComputerModel {

	private int id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private CompanyModel companyModel;
	
	
	public ComputerModel(int id, String name, Timestamp introduced, Timestamp discontinued, CompanyModel companyModel) {
	
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyModel = companyModel;
	}
	
	public ComputerModel( String name,Timestamp introduced, Timestamp discontinued, CompanyModel companyModel) {
		
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyModel = companyModel;
	}
	
	public ComputerModel(){
		
	}
	
	/**
	 * 
	 * @return integer id
	 */
	public int getId() {
		return id;
	}
	/**
	 * set Id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return computer name 
	 */
	public String getName() {
		return name;
	}
	/**
	 * set computer name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get Introduced timestamp
	 * @return
	 */
	public Timestamp getIntroduced() {
		return introduced;
	}
	/**
	 * setIntroduced timestamp
	 * @param introduced
	 */
	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}
	/**
	 * get Discontinued timestamp
	 * @return
	 */
	public Timestamp getDiscontinued() {
		return discontinued;
	}
	/**
	 * set discontinued timestamp
	 * @param discontinued
	 */
	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}
	/**
	 * get company companyModel
	 * @return
	 */
	public CompanyModel getCompanyModel() {
		return companyModel;
	}
	/**
	 * set company Id
	 * @param companyId
	 */
	public void setCompanyModel(CompanyModel companyModel) {
		this.companyModel = companyModel;
	}
	
	/**
	 * override toString method
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("id: "+id +",");
		strBuilder.append("name: "+this.name+ ",");
		strBuilder.append("introduced: "+this.introduced+ ",");
		strBuilder.append("discontinued: "+this.discontinued+ ",");
		if(companyModel!=null){
			strBuilder.append("companyId: "+this.companyModel.getId());
			strBuilder.append("companyName: "+this.companyModel.getName());
		}else{
			strBuilder.append("companyId: "+null);
			strBuilder.append("companyName: "+null);
		}
		
		return strBuilder.toString();
	}
	
	
}
