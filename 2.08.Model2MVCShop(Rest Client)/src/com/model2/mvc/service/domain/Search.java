package com.model2.mvc.service.domain;


public class Search {
	
	///Field
	private int curruntPage;
	private String searchCondition;
	private String searchKeyword;
	private int pageSize;
	private String ordering;
	private int endRowNum;
	private int startRowNum;
	
	
	///Constructor
	public Search() {
	}
	
	///Method
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int paseSize) {
		this.pageSize = paseSize;
	}
	
	public int getCurrentPage() {
		return curruntPage;
	}
	public void setCurrentPage(int curruntPage) {
		this.curruntPage = curruntPage;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getOrdering() {
		return ordering;
	}

	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}
	
	public String switchingOrdering(String ordering) {
		
		if(ordering.contains("ASC")) {
			ordering="DESC";
		}else if(ordering.contains("DESC")) {
			ordering ="";
		}else if(!(ordering.contains("ASC") && ordering.contains("DESC") ) ){
			ordering ="ASC";
		}
		
		return ordering;
	}
	
	public int getEndRowNum() {
		return curruntPage*pageSize;
	}

	public int getStartRowNum() {
		return (curruntPage-1)*pageSize+1;
	}


	@Override
	public String toString() {
		return "Search [curruntPage=" + curruntPage + ", searchCondition=" + searchCondition + ", searchKeyword="
				+ searchKeyword + ", pageSize=" + pageSize + ", ordering=" + ordering + "]";
	}
}