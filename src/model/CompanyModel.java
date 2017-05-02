package model;

public class CompanyModel {

	
	private int id;
	private String name;
	
	
	public CompanyModel(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("id: "+id +",");
		strBuilder.append("name "+this.name+ ",");
	
		return strBuilder.toString();
	}
	
	
}
