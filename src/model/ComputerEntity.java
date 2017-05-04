package model;

import java.time.LocalDate;

public class ComputerEntity {

	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private CompanyEntity companyModel;
	
	
	public ComputerEntity(ComputerBuilder computerBuilder) {
	
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.discontinued = computerBuilder.discontinued;
		this.companyModel = computerBuilder.company;
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
	public LocalDate getIntroduced() {
		return introduced;
	}
	/**
	 * setIntroduced timestamp
	 * @param introduced
	 */
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	/**
	 * get Discontinued timestamp
	 * @return
	 */
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	/**
	 * set discontinued timestamp
	 * @param discontinued
	 */
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	/**
	 * get company companyModel
	 * @return
	 */
	public CompanyEntity getCompanyEntity() {
		return companyModel;
	}
	/**
	 * set company Id
	 * @param companyId
	 */
	public void setCompanyEntity(CompanyEntity companyModel) {
		this.companyModel = companyModel;
	}
	
	
	public static class ComputerBuilder {
		
		
		private int id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private CompanyEntity company;
 
        public ComputerBuilder() {
            
        }
 
        public ComputerBuilder id(int id){
        	this.id = id;
        	return this;
        }
        public ComputerBuilder name(String name) {
            this.name = name;
            return this;
        }
 
        public ComputerBuilder introduced(LocalDate introduced) {
            this.introduced = introduced;
            return this;
        }
 
        public ComputerBuilder discontinued(LocalDate introduced) {
            this.discontinued = introduced;
            return this;
        }
        public ComputerBuilder company(CompanyEntity company) {
            this.company= company;
            return this;
        }
        
        
 
        public ComputerEntity build() {
            return new ComputerEntity(this);
        }
 
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
