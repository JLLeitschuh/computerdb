package dto;

import java.time.LocalDate;

import model.CompanyEntity;
import model.ComputerEntity;
import model.ComputerEntity.ComputerBuilder;

public class ComputerDTO {

	private int id;
	private String name;
	private String introduced;
	private String discontinued;
	private int companyId;
	private String companyName;

	public ComputerDTO(ComputerDTOBuilder computerBuilder) {

		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.discontinued = computerBuilder.discontinued;
		this.companyId = computerBuilder.companyId;
		this.companyName = computerBuilder.companyName;
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




	public String getIntroduced() {
		return introduced;
	}




	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}




	public String getDiscontinued() {
		return discontinued;
	}




	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}




	public int getCompanyId() {
		return companyId;
	}




	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}




	public String getCompanyName() {
		return companyName;
	}




	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}




	public static class ComputerDTOBuilder {


		private int id;
		private String name;
		private String introduced;
		private String discontinued;
		private int companyId;
		private String companyName;

		public ComputerDTOBuilder() {

		}

		public ComputerDTOBuilder id(int id){
			this.id = id;
			return this;
		}
		public ComputerDTOBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ComputerDTOBuilder introduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerDTOBuilder discontinued(String introduced) {
			this.discontinued = introduced;
			return this;
		}
		public ComputerDTOBuilder companyId(int companyId) {
			this.companyId= companyId;
			return this;
		}

		public ComputerDTOBuilder companyName(String companyName) {
			this.companyName= companyName;
			return this;
		}


		public ComputerDTO build() {
			return new ComputerDTO(this);
		}

	}
}
