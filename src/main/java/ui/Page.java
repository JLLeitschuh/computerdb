package ui;

import java.util.ArrayList;

import model.ComputerEntity;

public class Page {

	
	private ArrayList<ComputerEntity> computerModelList ;
	private int numberItemPerPage =20;
	private int numberPage = 0;
	private int currentPage=0;
	
	
	
	public Page(){
		this.computerModelList = new ArrayList<ComputerEntity>();
	}
	
	public void setNumberPage(int numberItemPerPage ){
		this.numberItemPerPage = numberItemPerPage;
		this.numberPage = computerModelList.size()/this.numberItemPerPage;
	}
	
	public void setCurentPage(int currentPage){
		this.currentPage = currentPage;
	}
	
	
	
}
