package itu.datamining.week3_classification;

import java.util.ArrayList;
import java.util.EnumSet;

import itu.datamining.week3_classification.Mushroom;

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
		for (Mushroom mushroom : mushrooms) {
			System.out.println(mushroom.m_bruises);
		}
	}

}
