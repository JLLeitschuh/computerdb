package com.ebiz.computerdatabase.model;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


public class PageRequest {

	String research;
	int page;
	int itemNumber;
	String orderBy;
	int sort;
	public static final int DEFAULT_NUMBER_PAGE = 1;
	public static final int DEFAULT_ITEM_NUMBER = 10;
	public static final int DEFAULT_SORT = 0;



	public PageRequest(HttpServletRequest request) {

		this.research = request.getParameter("search");
		setPage(request.getParameter("page"));
		setItemNumber(request.getParameter("item_number"));
		setOrderBy(request.getParameter("orderby"), request.getParameter("order"));
	}

	public void setPage(String page) {
		if (page == null || !StringUtils.isNumeric(page)) {
			this.page = DEFAULT_NUMBER_PAGE;
		} else {
			this.page = Integer.parseInt(page);
		}
	}

	public void setItemNumber(String itemNumber) {
		if (itemNumber == null || !StringUtils.isNumeric(itemNumber)) {
			this.itemNumber = DEFAULT_ITEM_NUMBER;
		} else {
			this.itemNumber = Integer.parseInt(itemNumber);
		}
	}

	public void setOrderBy(String orderBy,String sort) {

		this.orderBy = orderBy;
		if (sort == null || !StringUtils.isNumeric(sort)) {
			this.sort = DEFAULT_SORT;
		} else {
			this.sort =Integer.parseInt(sort);
		}
	}

	public int getItemNumber(){
		return itemNumber;
	}
	public int getPage() {
		return page;
	}

	public String getOrderBy() {

		return orderBy;
	}

	public int getSort() {

		return sort;
	}

	public String getResearch() {

		return research;
	}

}
