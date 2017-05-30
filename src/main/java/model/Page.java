package model;

import java.util.List;

public class Page<T> {

	int numberTotalItems = 0;
	List<T> items;
	int numberPage;
	PageRequest pageRequest;

	public PageRequest getPageRequest() {
		return pageRequest;
	}


	public void setNumberPage(int numberPage) {
		this.numberPage = numberPage;
	}

	public int getNumberTotalItems() {
		return numberTotalItems;
	}

	public void setNumberTotalItems(int numberTotalItems,PageRequest pageRequest) {
		this.numberTotalItems = numberTotalItems;
		this.pageRequest = pageRequest;
		updateNumberPage(pageRequest.itemNumber);
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
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
