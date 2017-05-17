package ui;

public class Page {

	private int numberItemPerPage = 10;
	private int numberPage = 0;
	private int currentPage = 1;
	private int startPage = 1;
	private int endPage = 5;

	private int numberOfTotalItem = -1;

	/**
	 * set Pagination currentPage and reinitialized startPage and endPage parameter.
	 * @param currentPage .
	 */
	public void setCurrentPage(int currentPage) {

		if (currentPage <= 0) {
			this.currentPage = 1;
		} else if (currentPage >= this.numberPage) {
			this.currentPage = this.numberPage - 1;
		} else {

			this.currentPage = currentPage;
		}
		if (this.currentPage < startPage) {
			startPage = this.currentPage;
			endPage = startPage + 5;
		}
		if (this.currentPage > endPage) {
			endPage = this.currentPage;
			startPage = endPage - 5;
		} else if (this.numberPage > endPage && this.currentPage == endPage) {
			// increment start page
			startPage++;
			endPage = startPage + 5;

		} else if (this.currentPage == startPage && startPage > 1) {
			startPage--;
			endPage = startPage + 5;
		}

	}

	public int getNumberOfTotalItem() {
		return this.numberOfTotalItem;
	}

	/**
	 * set number of total item and update Number of page.
	 * @param numberOfTotalItem .
	 */
	public void setNumberOfTotalItem(int numberOfTotalItem) {
		this.numberOfTotalItem = numberOfTotalItem;
		updateNumberPage();
	}

	/**
	 * update number of page.
	 */
	public void updateNumberPage() {
		this.numberPage = this.numberOfTotalItem / this.numberItemPerPage;
	}

	/**
	 * set number of item by page and update Number of page.
	 * @param numberItemPage .
	 */
	public void setNumberItemPage(int numberItemPage) {
		this.numberItemPerPage = numberItemPage;
		updateNumberPage();
	}

	public int getNumberItemPerPage() {
		return numberItemPerPage;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	/**
	 * update and return numberPage.
	 * @return numberPage
	 */
	public int getNumberPage() {
		return this.numberPage;
	}

	/**
	 *  get endPage number.
	 * @return endPage number
	 */
	public int endPage() {
		return this.endPage;
	}

	/**
	 * get startPage number.
	 * @return startPage number
	 */
	public int startPage() {
		return this.startPage;
	}

}
