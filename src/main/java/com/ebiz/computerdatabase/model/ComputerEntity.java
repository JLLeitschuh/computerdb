package com.ebiz.computerdatabase.model;

import java.time.LocalDate;

public class ComputerEntity {

	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private int companyId;
	private String companyName;

	/**
	 * ComputerEntity constructor.
	 * @param computerBuilder
	 *            .
	 */
	public ComputerEntity(ComputerBuilder computerBuilder) {

		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.discontinued = computerBuilder.discontinued;
		this.companyId = computerBuilder.companyId;
		this.companyName = computerBuilder.companyName;
	}

	/**
	 * return computer Builder.
	 * @return Builder .
	 */
	public static ComputerBuilder computerBuilder() {

		return new ComputerBuilder();
	}

	/**
	 * get computer Id.
	 * @return id from computer
	 */
	public int getId() {
		return id;
	}

	/**
	 * set computer Id.
	 * @param id
	 *            .
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get computer name.
	 * @return computer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set computer name.
	 * @param name
	 *            .
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get Introduced timestamp.
	 * @return Localdate.
	 */
	public LocalDate getIntroduced() {
		return introduced;
	}

	/**
	 * setIntroduced timestamp.
	 * @param introduced
	 *            .
	 */
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	/**
	 * get Discontinued timestamp.
	 * @return disontinued localdate
	 */
	public LocalDate getDiscontinued() {
		return discontinued;
	}

	/**
	 * set discontinued timestamp.
	 * @param discontinued
	 *            .
	 */
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * get company companyModel.
	 * @return company entity
	 */
	public int getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * ComputerEntity Builder.
	 * @author ebiz
	 *
	 */

	public static class ComputerBuilder {

		private int id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private int companyId;
		private  String companyName;

		/**
		 * Builder constructor.
		 */
		public ComputerBuilder() {
		}

		/**
		 * set computer id.
		 * @param id .
		 * @return computer builder
		 */
		public ComputerBuilder id(int id) {
			this.id = id;
			return this;
		}

		/**
		 * set name.
		 * @param name .
		 * @return computer builder
		 */
		public ComputerBuilder name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * set introduced Local Date.
		 * @param introduced .
		 * @return computer builder
		 */
		public ComputerBuilder introduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		/**
		 * set discontinued.
		 * @param discontinued .
		 * @return computer builder
		 */
		public ComputerBuilder discontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		/**
		 * set company entity.
		 * @param companyId .
		 * @return computer builder
		 */

		public ComputerBuilder companyId(int companyId) {
			this.companyId = companyId;
			return this;
		}

		public ComputerBuilder companyName(String companyName) {
			this.companyName = companyName;
			return this;
		}

		/**
		 * create computer entity from builder.
		 * @return computer entity.
		 */
		public ComputerEntity build() {
			return new ComputerEntity(this);
		}

	}

	/**
	 * override toString method.
	 * @return overriden string
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("id: " + id + ",");
		strBuilder.append("name: " + this.name + ",");
		strBuilder.append("introduced: " + this.introduced + ",");
		strBuilder.append("discontinued: " + this.discontinued + ",");
		strBuilder.append("companyId" +this.companyId);


		return strBuilder.toString();
	}

}
