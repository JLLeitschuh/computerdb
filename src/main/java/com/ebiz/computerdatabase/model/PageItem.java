package com.ebiz.computerdatabase.model;

import java.util.List;

public class PageItem<T> {

	long numberTotalItems = 0;
	List<T> items;
	int numberPage;
	PageRequest pageRequest;

	public PageItem(long numberTotalItems, List<T> items, int numberPage) {
		this.numberTotalItems = numberTotalItems;
		this.items = items;
		this.numberPage = numberPage;

	}

	public PageRequest getPageRequest() {
		return pageRequest;
	}


	public void setNumberPage(int numberPage) {
		this.numberPage = numberPage;
	}

	public long getNumberTotalItems() {
		return numberTotalItems;
	}

	public void setNumberTotalItems(int numberTotalItems, PageRequest pageRequest) {
		this.numberTotalItems = numberTotalItems;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}


	public int getNumberPage() {

		return numberPage > 0 ? numberPage : 1;
	}

}
