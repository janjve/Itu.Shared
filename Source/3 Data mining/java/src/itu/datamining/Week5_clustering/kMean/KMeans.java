package itu.datamining.Week5_clustering.kMean;
import java.util.ArrayList;

import itu.datamining.Week5_clustering.data.*;


public class KMeans {

	public static ArrayList<KMeanCluster> KMeansPartition(int k, ArrayList<Iris> data)
	{
		ArrayList<KMeanCluster> kMeansPartitions = new ArrayList<>();

		NormalizeIrisData(data);

		kMeansPartitions = GetInitialPartition(k, data);

		//newKMeansPartitions = reassignClusters(kMeansPartitions, data);

		boolean swapsOccured = false;

		do {
			//Creating the new partitions
			ArrayList<KMeanCluster> newKMeansPartitions = reassignClusters(kMeansPartitions, data);

			//Checking if the new partitions are different from the last
			swapsOccured = false;
			int size = newKMeansPartitions.size();
			for(int i = 0; i < size; i ++) {
				if(!newKMeansPartitions.get(i).equals(kMeansPartitions.get(i))) {
					swapsOccured = true;
					break;
				}
			}
			kMeansPartitions = newKMeansPartitions;
			System.out.println("Loop");
		} while(swapsOccured);

		for (KMeanCluster cluster : kMeansPartitions) {
			System.out.println(cluster.toString());
		}
		return kMeansPartitions;
	}

	public static ArrayList<KMeanCluster> reassignClusters(ArrayList<KMeanCluster> clusters, ArrayList<Iris> data) {
		ArrayList<KMeanCluster> newClusters = new ArrayList<>();
		int size = clusters.size();

		for (int i = 0; i < size; i++) {
			KMeanCluster newCluster = new KMeanCluster();
			newCluster.setClusterMean(clusters.get(i).calculateClusterMean());
			newClusters.add(newCluster);
		}

		for (Iris iris : data) {
			KMeanCluster closestCluster = newClusters.get(0);
			for(KMeanCluster currentCluster : newClusters) {
				if(euclideanDistance(iris, currentCluster.ClusterMean) < euclideanDistance(iris, closestCluster.ClusterMean)) {
					closestCluster = currentCluster;
				}
			}
			closestCluster.ClusterMembers.add(iris);
		}
		return newClusters;
	}

	//Calculates the euclidean distance. Calculating Manhattan distance would problably be quicker.
	public static double euclideanDistance(Iris iris, Iris cluster) {
		double res = Math.sqrt(Math.pow((double)(iris.Petal_Length - cluster.Petal_Length),2) +
				Math.pow((double)(iris.Petal_Width - cluster.Petal_Width),2) +
				Math.pow((double)(iris.Sepal_Width - cluster.Sepal_Width),2) +
				Math.pow((double)(iris.Sepal_Length - cluster.Sepal_Length),2));

		return res;
	}

	//Gets the initial partitions
	public static ArrayList<KMeanCluster> GetInitialPartition(int k, ArrayList<Iris> data)
	{
		ArrayList<KMeanCluster> kMeansPartitions = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			KMeanCluster kMeanCluster = new KMeanCluster();
			kMeanCluster.ClusterMembers.add(data.get(i));
			kMeansPartitions.add(kMeanCluster);
		}
		return kMeansPartitions;
	}

	//Normalizes the Iris data
	public static void NormalizeIrisData(ArrayList<Iris> data)
	{
		float petalLength_max = Float.MIN_VALUE;
		float petalLength_min = Float.MAX_VALUE;

		float petalWidth_max = Float.MIN_VALUE;
		float petalWidth_min = Float.MAX_VALUE;

		float sepalLength_max = Float.MIN_VALUE;
		float sepalLength_min = Float.MAX_VALUE;

		float sepalWidth_max = Float.MIN_VALUE;
		float sepalWidth_min = Float.MAX_VALUE;

		for(Iris iris :data) {
			petalLength_max = Math.max(petalLength_max, iris.Petal_Length);
			petalLength_min = Math.min(petalLength_min, iris.Petal_Length);

			petalWidth_max = Math.max(petalWidth_max, iris.Petal_Width);
			petalWidth_min = Math.min(petalWidth_min, iris.Petal_Width);

			sepalLength_max = Math.max(sepalLength_max, iris.Sepal_Length);
			sepalLength_min = Math.min(sepalLength_min, iris.Sepal_Length);

			sepalWidth_max = Math.max(sepalWidth_max, iris.Sepal_Width);
			sepalWidth_min = Math.min(sepalWidth_min, iris.Sepal_Width);
		}

		for(Iris iris :data) {
			iris.Petal_Length = (iris.Petal_Length - petalLength_min) / (petalLength_max - petalLength_min);
			iris.Petal_Width = (iris.Petal_Width - petalWidth_min) / (petalWidth_max - petalWidth_min);
			iris.Sepal_Length = (iris.Sepal_Length - sepalLength_min) / (sepalLength_max - sepalLength_min);
			iris.Sepal_Width = (iris.Sepal_Width - sepalWidth_min) / (sepalWidth_max - sepalWidth_min);
		}
	}
}
