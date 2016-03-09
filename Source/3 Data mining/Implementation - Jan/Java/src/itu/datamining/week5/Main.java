package itu.datamining.week5;

import itu.datamining.week5.data.DataLoader;
import itu.datamining.week5.data.Iris;
import itu.datamining.week5.kMean.KMeanCluster;
import itu.datamining.week5.kMean.KMeans;
import itu.datamining.week5.kMedoid.KMedoid;
import itu.datamining.week5.kMedoid.KMedoidCluster;

import java.util.ArrayList;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//First step load in iris data
		ArrayList<Iris> irisData = DataLoader.LoadAllIrisData();

		// Normalize
		normalize(irisData);

		//Second step --> do the clustering using k-means!
		ArrayList<KMeanCluster> FoundClusters_KMeans = KMeans.KMeansPartition(3, irisData);

		for(KMeanCluster cluster : FoundClusters_KMeans){
			System.out.println(cluster);
		}
		
		//Third step --> do the clustering using k-medoids!
		ArrayList<KMedoidCluster> FoundClusters_KMedoids = KMedoid.KMedoidPartition(3, irisData);
	}

	public static void normalize(ArrayList<Iris> data){

		// Normalize
		float sepalLength_min, sepalWidth_min, petalLength_min, petalWidth_min;
		sepalLength_min = sepalWidth_min = petalLength_min = petalWidth_min= Float.MAX_VALUE;

		float sepalLength_max, petalLength_max, sepalWidth_max, petalWidth_max;
		sepalLength_max = petalLength_max = sepalWidth_max = petalWidth_max = Float.MIN_VALUE;

		// Get max
		for(Iris iris : data){
			sepalLength_min = Float.min(iris.Sepal_Length, sepalLength_min);
			sepalLength_max = Float.max(iris.Sepal_Length, sepalLength_max);

			sepalWidth_min = Float.min(iris.Sepal_Width, sepalWidth_min);
			sepalWidth_max = Float.max(iris.Sepal_Width, sepalWidth_max);

			petalLength_min = Float.min(iris.Petal_Length, petalLength_min);
			petalLength_max = Float.max(iris.Sepal_Length, petalLength_max);

			petalWidth_min = Float.min(iris.Petal_Width, petalWidth_min);
			petalWidth_max = Float.max(iris.Petal_Width, petalWidth_max);
		}

		// Normalized value
		for(Iris iris : data){
			iris.Sepal_Length = (iris.Sepal_Length - sepalLength_min) / (sepalLength_max - sepalLength_min);
			iris.Sepal_Width = (iris.Sepal_Width - sepalWidth_min) / (sepalWidth_max - sepalWidth_min);
			iris.Petal_Length = (iris.Petal_Length - petalLength_min) / (petalLength_max - petalLength_min);
			iris.Petal_Width = (iris.Petal_Width - petalWidth_min) / (petalWidth_max - petalWidth_min);
		}
	}
}
