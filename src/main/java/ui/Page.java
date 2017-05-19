package ui;

import java.util.List;

public class Page<T> {

	int numberTotalItems = 0;
	List<T> items;
	int currentPage = 1;
	int numberPage;

	public int getNumberTotalItems() {
		return numberTotalItems;
	}

	public void setNumberTotalItems(int numberTotalItems) {
		this.numberTotalItems = numberTotalItems;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * update number page.
	 * @param itemPerPage .
	 */

	public void updateNumberPage(int itemPerPage) {

		if (itemPerPage > 0) {

			numberPage = (int) Math.ceil((float) ((float) numberTotalItems / (float) itemPerPage));
		}
	}

	public int getNumberPage() {

		return numberPage > 0 ? numberPage : 1;
	}

}
