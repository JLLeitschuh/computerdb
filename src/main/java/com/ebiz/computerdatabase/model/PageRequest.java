package com.ebiz.computerdatabase.model;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class PageRequest {

	String search;
	int page =1;
	int itemNumber=10;
	String orderBy;
	int sort =1;
	public static final int DEFAULT_NUMBER_PAGE = 1;
	public static final int DEFAULT_ITEM_NUMBER = 10;
	public static final int DEFAULT_order = 0;



	public PageRequest(Map<String,String> map) {

		this.search = map.get("search");
		setPage(map.get("page"));
		setItem_number(map.get("itemNumber"));
		setOrderBy(map.get("orderby"), map.get("sort"));
	}


	public void setPage(String page) {
		if (page == null || !StringUtils.isNumeric(page)) {
			this.page = DEFAULT_NUMBER_PAGE;
		} else {
			this.page = Integer.parseInt(page);
		}
	}

	public void setItem_number(String item_number) {
		if (item_number == null || !StringUtils.isNumeric(item_number)) {
			this.itemNumber = DEFAULT_ITEM_NUMBER;
		} else {
			this.itemNumber = Integer.parseInt(item_number);
		}
	}

	public void setOrderBy(String orderBy,String order) {

		this.orderBy = orderBy;
		if (order == null || !StringUtils.isNumeric(order)) {
			this.sort = DEFAULT_order;
		} else {
			this.sort =Integer.parseInt(order);
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

	public String getSearch() {

		return search;
	}

}
