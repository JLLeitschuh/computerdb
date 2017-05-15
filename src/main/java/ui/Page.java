package ui;

import java.util.List;

import dto.ComputerDTO;


public class Page {

	private List<ComputerDTO> computerList;
	private int numberItemPerPage = 10;
	private int numberPage = 0;
	private int currentPage = 1;
	private int startPage = 1;
	private int endPage = 5;



	public void setNumberItemPage(int numberItemPage) {
		this.numberItemPerPage = numberItemPage;
	}

	/**
	 * set Pagination currentPage and reinitialized startPage and endPage parameter.
	 * @param currentPage .
	 */
	public void setCurentPage(int currentPage) {

		if (currentPage >= 1 && currentPage < numberPage) {

			this.currentPage = currentPage;
		}
		if (currentPage < startPage && currentPage > 1) {
			startPage = currentPage;
			endPage = startPage + 5;
		}
		if (currentPage > endPage && currentPage < numberPage) {
			endPage = currentPage;
			startPage = endPage - 5;
		}
		if (numberPage > endPage && currentPage == endPage) {
			// increment start page
			startPage++;
			endPage = startPage + 5;

		} else if (currentPage == startPage && startPage > 1) {
			startPage--;
			endPage = startPage + 5;
		}

	}

	/**
	 * set Computer List and update numberPage.
	 * @param computerList .
	 */
	public void setComputerList(List<ComputerDTO> computerList) {
		this.computerList = computerList;
		this.numberPage = computerList.size() / this.numberItemPerPage;
	}

	public int getNumberItemPerPage() {
		return numberItemPerPage;
	}

	public void setNumberItemPerPage(int numberItemPerPage) {
		this.numberItemPerPage = numberItemPerPage;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public List<ComputerDTO> getComputerList() {
		return computerList;
	}

	/**
	 * update and return numberPage.
	 * @return numberPage
	 */
	public int getNumberPage() {
		this.numberPage = computerList.size() / this.numberItemPerPage;
		return numberPage;
	}

	/**
	 *  get endPage number.
	 * @return endPage number
	 */
	public int endPage() {
		return endPage;
	}

	/**
	 * get startPage number.
	 * @return startPage number
	 */
	public int startPage() {
		return startPage;
	}

}
