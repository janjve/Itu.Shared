package itu.datamining.Week5_clustering;

import java.util.ArrayList;

import itu.datamining.Week5_clustering.data.*;
import itu.datamining.Week5_clustering.kMean.KMeanCluster;
import itu.datamining.Week5_clustering.kMean.KMeans;
import itu.datamining.Week5_clustering.kMedoid.KMedoid;
import itu.datamining.Week5_clustering.kMedoid.KMedoidCluster;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//First step load in iris data
		ArrayList<Iris> irisData = DataLoader.LoadAllIrisData();
		
		//Second step --> do the clustering using k-means!
		ArrayList<KMeanCluster> FoundClusters_KMeans = KMeans.KMeansPartition(3, irisData);
		
		//Third step --> do the clustering using k-medoids!
		ArrayList<KMedoidCluster> FoundClusters_KMedoids = KMedoid.KMedoidPartition(3, irisData);
	}

}
