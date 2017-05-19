package ui;

import exception.DTOException;

public class Main {

	/**
	 * main.
	 * @param args .
	 * @throws DTOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, DTOException {

		Menu menu = new Menu();
		menu.chooseMenu();

	}
}
