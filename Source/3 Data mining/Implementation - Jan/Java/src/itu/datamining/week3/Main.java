package itu.datamining.week3;

import com.sun.deploy.security.DecisionTime;
import itu.datamining.week3.classification.ID3Classifier;
import itu.datamining.week3.classification.InformationGain;
import itu.datamining.week3.classification.entities.DecisionTree;

import java.util.ArrayList;

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
		ArrayList<Object> attributes = Mushroom.getAttributeList();
		int attributeIndex = 2;

		for (Mushroom mushroom: mushrooms) {
			System.out.println(String.format("%s: %s", attributes.get(attributeIndex), mushroom.getAttributeValue(attributes.get(attributeIndex))));
		}

		DecisionTree tree = new DecisionTree();
		tree.generateDecisionTree(mushrooms, attributes, new InformationGain());



		System.out.println("DataManager loaded "+mushrooms.size() + " mushrooms");
		
	}

}
