package itu.datamining.week3.utility;

import java.util.ArrayList;
import java.util.EnumSet;

import itu.datamining.week3.utility.Mushroom;

/**
 * Main class to run program from.
 * 
 * @author Anders Hartzen
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// First step - Load data and convert to Mushroom objects.
		ArrayList<Mushroom> mushrooms = DataManager.LoadData();

		System.out.println("DataManager loaded "+mushrooms.size() + " mushrooms");
		
	}

}
