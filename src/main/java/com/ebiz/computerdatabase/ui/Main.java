package com.ebiz.computerdatabase.ui;

;import com.ebiz.computerdatabase.exception.DAOException;

public class Main {

	/**
	 * main.
	 * @param args .
	 * @throws DAOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, DAOException {

		Menu menu = new Menu();
		menu.chooseMenu();

	}
}
