package com.ebiz.computerdatabase.dto;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


public class ComputerDTO implements Serializable {


	private int id;

	private String name;


	private String introduced;


	private String discontinued;

	private int companyId;
	private String companyName;

	public ComputerDTO(){

	}
	/**
	 * Computer DTO constructor.
	 * @param computerBuilder .
	 */
	public ComputerDTO(ComputerDTOBuilder computerBuilder) {

		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.discontinued = computerBuilder.discontinued;
		this.companyId = computerBuilder.companyId;
		this.companyName = computerBuilder.companyName;

	}

	public static ComputerDTOBuilder getComputerDtoBuilder() {

		return new ComputerDTOBuilder();
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

		/**
		 * computer DTO Builder constructor.
		 */
		public ComputerDTOBuilder() {

		}

		/**
		 * set computerDTO builder  id.
		 * @param id .
		 * @return computer dto builder object
		 */

		public ComputerDTOBuilder id(int id) {
			this.id = id;
			return this;
		}

		/**
		 * set computerDTO builder name.
		 * @param name .
		 * @return computer dto builder object
		 */

		public ComputerDTOBuilder name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * set introduced date.
		 * @param introduced .
		 * @return computer dto builder object
		 */

		public ComputerDTOBuilder introduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		/**
		 * set discontinued date.
		 * @param discontinued .
		 * @return computer dto builder object
		 */

		public ComputerDTOBuilder discontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		/**
		 * set company id.
		 * @param companyId .
		 * @return computer dto builder object
		 */

		public ComputerDTOBuilder companyId(int companyId) {
			this.companyId = companyId;
			return this;
		}
		public ComputerDTOBuilder companyName(String companyName){
			this.companyName = companyName;
			return this;
		}


		/**
		 * build computerDTO object.
		 * @return new Computer DTO object.
		 */
		public ComputerDTO build() {
			return new ComputerDTO(this);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			ComputerDTOBuilder that = (ComputerDTOBuilder) o;

			if (id != that.id) return false;
			if (companyId != that.companyId) return false;
			if (!name.equals(that.name)) return false;
			if (introduced != null ? !introduced.equals(that.introduced) : that.introduced != null) return false;
			if (discontinued != null ? !discontinued.equals(that.discontinued) : that.discontinued != null)
				return false;
			return companyName != null ? companyName.equals(that.companyName) : that.companyName == null;
		}

		@Override
		public int hashCode() {
			int result = id;
			result = 31 * result + name.hashCode();
			result = 31 * result + (introduced != null ? introduced.hashCode() : 0);
			result = 31 * result + (discontinued != null ? discontinued.hashCode() : 0);
			result = 31 * result + companyId;
			result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
			return result;
		}

		@Override
		public String toString() {
			return "ComputerDTOBuilder{" +
					"id=" + id +
					", name='" + name + '\'' +
					", introduced='" + introduced + '\'' +
					", discontinued='" + discontinued + '\'' +
					", companyId=" + companyId +
					", companyName='" + companyName + '\'' +
					'}';
		}
	}
}
