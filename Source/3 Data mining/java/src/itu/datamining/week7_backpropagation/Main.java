import java.util.ArrayList;

import itu.datamining.week7_backpropagation.backPropagation.IrisNormalizer;
import itu.datamining.week7_backpropagation.data.DataLoader;
import itu.datamining.week7_backpropagation.data.Iris;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//First step load in iris data
		ArrayList<Iris> irisData = DataLoader.LoadAllIrisData();
		System.out.println("Successfully loaded "+irisData.size()+" iris flowers");
		IrisNormalizer.NormalizeIrisData(irisData);

		//Second step make perceptron or neural network
		// Input layer


	}
}
