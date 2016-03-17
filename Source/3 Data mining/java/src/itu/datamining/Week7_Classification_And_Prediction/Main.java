package itu.datamining.Week7_Classification_And_Prediction;

import itu.datamining.Week5_clustering.data.DataLoader;
import itu.datamining.Week5_clustering.data.Iris;
import itu.datamining.Week5_clustering.data.IrisNormalizer;

import java.util.ArrayList;



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

    }

}
