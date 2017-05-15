package model;

public class CompanyEntity {

	
	private int id;
	private String name;
	
	
	public CompanyEntity(int id, String name) {
		
		this.id = id;
		this.name = name;
	}
	/**
	 * get Company id
	 * @return integer
	 */
	public int getId() {
		return id;
	}
	/**
	 * set company id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * get company name
	 * @return String
	 */
	public String getName() {
		return name;
	}
	/**
	 * set company name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Override toString method
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("id: "+id +",");
		strBuilder.append("name "+this.name+ ",");
	
		return strBuilder.toString();
	}
	
	
}
