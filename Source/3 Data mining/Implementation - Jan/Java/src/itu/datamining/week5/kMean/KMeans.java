package itu.datamining.week5.kMean;
import itu.datamining.week5.data.Iris;
import itu.datamining.week5.kMedoid.ReassignedResponse;

import java.util.ArrayList;
import java.util.Hashtable;

public class KMeans {

	public static ArrayList<KMeanCluster> KMeansPartition(int k, ArrayList<Iris> data)
	{
		ArrayList<KMeanCluster> clusters = getInitialPartioningCluster(k, data);

		ReassignedResponse response = null;
		do{
			response = reassignDataToCluster(data, clusters);
			clusters = response.newCluster;
		} while(response.reassigned);

		return clusters;
	}

	public static ReassignedResponse reassignDataToCluster(ArrayList<Iris> data, ArrayList<KMeanCluster> clusters){

		boolean reassigned = false;
		Hashtable<KMeanCluster, KMeanCluster> newOldClusterIndex = new Hashtable<>();

		for(KMeanCluster cluster : clusters){
			KMeanCluster newCluster = new KMeanCluster();
			newCluster.clusterMean = cluster.calcClusterMean();
			newOldClusterIndex.put(newCluster, cluster);
		}

		ArrayList<KMeanCluster> newCluster = new ArrayList<>(newOldClusterIndex.keySet());

		for(Iris record : data){
			KMeanCluster minManhattenDistanceCluster = null;
			float minDistance = Float.MAX_VALUE;

			for(KMeanCluster cluster : newOldClusterIndex.keySet()){
				float distance = manhattenDistance(record, cluster.clusterMean);
				if(minDistance > distance){
					minManhattenDistanceCluster = cluster;
					minDistance = distance;
				}
			}
			minManhattenDistanceCluster.ClusterMembers.add(record);

			if(!reassigned && !newOldClusterIndex.get(minManhattenDistanceCluster).ClusterMembers.contains(record)){
				reassigned = true;
			}
		}

		return new ReassignedResponse(reassigned, newCluster);
	}

	public static float manhattenDistance(Iris iris1, Iris iris2){
		return Math.abs(iris1.Sepal_Length - iris2.Sepal_Length)
				+ Math.abs(iris1.Sepal_Width - iris2.Sepal_Width)
				+ Math.abs(iris1.Petal_Length - iris2.Petal_Length)
				+ Math.abs(iris1.Petal_Width - iris2.Petal_Width);
	}

	public static ArrayList<KMeanCluster> getInitialPartioningCluster(int k, ArrayList<Iris> data) {
		ArrayList<KMeanCluster> initialClusters = new ArrayList<>();

		for (Iris initialData : data.subList(0, k)) {
			KMeanCluster cluster = new KMeanCluster();
			cluster.ClusterMembers.add(initialData);
			initialClusters.add(cluster);
		}

		return initialClusters;
	}
}


