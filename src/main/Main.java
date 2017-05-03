package main;

import java.sql.Timestamp;

import computerdb.MySQLConnectDB;
import model.ComputerModel;
import ui.Menu;
import utils.Utils;

public class Main {

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.chooseMenu();
					
	}
}
