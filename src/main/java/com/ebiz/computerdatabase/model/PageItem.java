package com.ebiz.computerdatabase.model;

import com.ebiz.computerdatabase.mapper.PageRequestMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Map;

public class PageItem<T> {

	private long numberTotalItems = 0;
	private List<T> items;
	private int numberPage;
	private int currentPage;
	private int itemNumber;
	private String search;
	private String orderBy;
	private int sort =1;
	public static final int DEFAULT_NUMBER_PAGE = 1;
	public static final int DEFAULT_ITEM_NUMBER = 10;
	public static final int DEFAULT_ORDER = 0;
	org.springframework.data.domain.PageRequest pageRequest ;



	public PageItem(Page<T> page,List<T> items,Map<String, String> map) {
		this.numberTotalItems = page.getTotalElements();
		this.items = items;
		this.numberPage = page.getTotalPages();
		this.currentPage = DEFAULT_NUMBER_PAGE;
		this.itemNumber = DEFAULT_ITEM_NUMBER;
		this.sort = DEFAULT_ORDER;
		String sortBy = map.get("sort");
		String orderBy = map.get("orderby");
		String search = map.get("search");


		if(  map.get("page") != null && StringUtils.isNumeric(map.get("page"))){
			currentPage = Integer.parseInt(map.get("page"));
		}

		if(  map.get("itemNumber") != null && StringUtils.isNumeric(map.get("itemNumber"))){
			this.itemNumber = Integer.parseInt(map.get("itemNumber"));
		}
		if(search!=null){
			this.search = search;
		}
		if(  sortBy != null && StringUtils.isNumeric(sortBy)){
			this.sort = Integer.parseInt(sortBy);
		}
		if(orderBy!=null){
			this.orderBy = orderBy;
		}


	}

	public org.springframework.data.domain.PageRequest getPageRequest(){
		return this.pageRequest;
	}
	public long getNumberTotalItems() {
		return numberTotalItems;
	}


	public List<T> getItems() {
		return items;
	}

	public int getNumberPage() {
		return numberPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public String getSearch() {
		return search;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public int getSort() {
		return sort;
	}
}
