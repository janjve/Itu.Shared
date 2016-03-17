package itu.datamining.Week5_clustering.kMean;
import java.util.ArrayList;

import itu.datamining.Week5_clustering.data.*;


public class KMeans {

	public static ArrayList<KMeanCluster> KMeansPartition(int k, ArrayList<Iris> data)
	{
		ArrayList<KMeanCluster> kMeansPartitions = new ArrayList<>();

		IrisNormalizer.NormalizeIrisData(data);

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
}
