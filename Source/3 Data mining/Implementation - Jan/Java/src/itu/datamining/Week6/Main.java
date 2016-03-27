package itu.datamining.Week6;

import java.util.ArrayList;

import itu.datamining.Week6.backPropagation.IrisNormalizer;
import itu.datamining.Week6.backPropagation.NeuralNetwork;
import itu.datamining.Week6.backPropagation.entities.NeuronReference;
import itu.datamining.Week6.data.DataLoader;
import itu.datamining.Week6.data.Iris;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//First step load in iris data
		ArrayList<Iris> irisData = DataLoader.LoadAllIrisData();
		System.out.println("Successfully loaded "+irisData.size()+" iris flowers");

		IrisNormalizer.NormalizeIrisData(irisData);

		NeuralNetwork neuralNetwork = new NeuralNetwork();
		System.out.println("Normalized iris:");

		irisData.forEach(System.out::println);
	}
}
